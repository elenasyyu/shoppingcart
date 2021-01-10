package mc.shoppingcart.service.impl;

import java.util.List;

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
			objNewCart.setName(requestedCart.getName());
		}
		
		// Clean up all the item, and replace with the one from the request
		objNewCart.getCartItems().clear();
		
		// Go through all the product to see if it is valid
		for (CartItem reqItem : requestedCart.getCartItems()) {
			// Create new item
			CartItem objNewItem = new CartItem();
			
			// Cart
			objNewItem.setCart(objNewCart);
			
			// Product
			Product reqProduct = productRepository.findFirstByName(reqItem.getProduct().getName());
			if (reqProduct == null)
				throw new IllegalArgumentException("Invalid product " + reqItem.getProduct().getName());
			
			if (reqProduct.getPrice() != reqItem.getProduct().getPrice())
				throw new IllegalArgumentException("Invalid product price" + reqItem.getProduct().getPrice() + 
						" for " + reqItem.getProduct().getName());
			
			objNewItem.setProduct(reqProduct);
			
			// Quantity
			objNewItem.setQuantity(reqItem.getQuantity());
			
			// Add to Cart
			objNewCart.getCartItems().add(objNewItem);
		};
		
		return cartRepository.save(objNewCart);
	}
}
