package mc.shoppingcart.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import mc.shoppingcart.entity.Cart;
import mc.shoppingcart.entity.CartItem;
import mc.shoppingcart.entity.Product;
import mc.shoppingcart.repository.CartRepository;
import mc.shoppingcart.repository.ProductRepository;
import mc.shoppingcart.repositorySpecs.CartSpecification;
import mc.shoppingcart.service.ICartService;

@Service
public class CartService implements ICartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Cart> getAllCarts() {
		Specification<Cart> spec = CartSpecification.ByName(null);
		return cartRepository.findAll(spec);
	}

	@Override
	public Cart getCart(String name) {
		Specification<Cart> spec = CartSpecification.ByName(name);
		return cartRepository.findOne(spec).orElseThrow(() -> new IllegalArgumentException("Invalid cart name " + name));
	}

	@Override
	public Cart createCart(Cart requestedCart)
		throws IllegalArgumentException {
		// Step 1:  Try to see if the card already existed...
		Cart objNewCart = cartRepository.findFirstByName(requestedCart.getName());
		
		// Step 2:  Create a new one if not existed
		if (objNewCart == null) {
			objNewCart = new Cart();
			
			// TODO... integrate the user later on...
			String cartName = (requestedCart.getName() != null ? requestedCart.getName() : generateCartName("user"));
			objNewCart.setName(cartName);
		}
		
		insertItems(requestedCart.getCartItems(), objNewCart);
		return cartRepository.save(objNewCart);
	}

	@Override
	public Cart updateCart(Cart requestedCart) throws IllegalArgumentException {
		// Step 1:  Try to see if the card already existed...
		Cart objUpdateCart = cartRepository.findFirstByName(requestedCart.getName());

		if (objUpdateCart == null)
			throw new IllegalArgumentException("Cart " + requestedCart.getName() + " not found!");

		insertItems(requestedCart.getCartItems(), objUpdateCart);
		return cartRepository.save(objUpdateCart);
	}
	
	@Override
	public Cart deleteItemsFromCart(final String cartName, List<String> productNames) 
		throws IllegalArgumentException {
		Cart objDeleteCart = cartRepository.findFirstByName(cartName);
		
		if (objDeleteCart == null)
			throw new IllegalArgumentException("Cart " + cartName + " not found!");
		
		productNames.forEach(name -> {
			for (CartItem objExistingItem : objDeleteCart.getCartItems()) {
				if (objExistingItem.getProduct().getName().equals(name)) {
					objDeleteCart.getCartItems().remove(objExistingItem);
					break;
				}
			}
		});
		
		return cartRepository.save(objDeleteCart);
	}

	@Override
	public void deleteCart(String cartName) throws IllegalArgumentException {
		Cart objDeleteCart = cartRepository.findFirstByName(cartName);

		if (objDeleteCart == null)
			throw new IllegalArgumentException("Cart " + cartName + " not found!");

		cartRepository.delete(objDeleteCart);
	}
		
	private void insertItems(final Set<CartItem> objFromItems, Cart objToCart) {
		// Clean up all the item, and replace with the one from the request
		objToCart.getCartItems().clear();
		
		// Go through all the product to see if it is valid
		for (CartItem fromItem : objFromItems) {
			// Create new item
			CartItem objNewItem = new CartItem();
			
			// Cart
			objNewItem.setCart(objToCart);
			
			// Product
			Product fromProduct = productRepository.findFirstByName(fromItem.getProduct().getName());
			if (fromProduct == null)
				throw new IllegalArgumentException("Invalid product " + fromItem.getProduct().getName());
		
			if (fromProduct.getPrice() != fromItem.getProduct().getPrice())
				throw new IllegalArgumentException("Invalid product price" + fromItem.getProduct().getPrice() + 
					" for " + fromItem.getProduct().getName());
			objNewItem.setProduct(fromProduct);
			
			// Quantity
			objNewItem.setQuantity(fromItem.getQuantity());
			
			// Add to Cart
			objToCart.getCartItems().add(objNewItem);
		};
	}
	
	private final String generateCartName(final String username) {
		return username + Instant.now().toEpochMilli();
	}
}
