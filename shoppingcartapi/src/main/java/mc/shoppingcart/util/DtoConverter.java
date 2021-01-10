package mc.shoppingcart.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.dto.ShoppingCartDetailDto;
import mc.shoppingcart.dto.ShoppingCartItemDto;
import mc.shoppingcart.entity.Cart;
import mc.shoppingcart.entity.CartItem;
import mc.shoppingcart.entity.Product;

public class DtoConverter {
	public static ProductDto convertProductToDto(final Product product) {
		ProductDto dto = new ProductDto();
		
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		
		return dto;
	}
	
	public static ShoppingCartDetailDto convertCartToDto(final Cart cart) {
		ShoppingCartDetailDto dto = new ShoppingCartDetailDto();
		
		dto.setName(cart.getName());
		
		List<ShoppingCartItemDto> itemsDto = new ArrayList<ShoppingCartItemDto>();
		cart.getCartItems().forEach(item-> {
			ShoppingCartItemDto itemDto = new ShoppingCartItemDto();
			
			Product product = item.getProduct();
			itemDto.setItemName(product != null ? product.getName() : null);
			itemDto.setItemPrice(product != null ? product.getPrice() : null);
			itemDto.setNumOfItem(item.getQuantity());
			
			itemsDto.add(itemDto);
		});
		dto.setItems(itemsDto);
		
		return dto;
	}
	
	public static Cart convertCartFromDto(ShoppingCartDetailDto dto) {
		Cart objCart = new Cart();
		
		// Note:  Leave the id as it will auto populate by the sequence if needed
		objCart.setName(dto.getName());
		
		Set<CartItem> items = new HashSet<CartItem>();
		dto.getItems().forEach(dtoItem -> {
			CartItem item = new CartItem();
			
			// Note:  Leave the id as it will auto populate by the sequence if needed
			
			// Cart
			item.setCart(objCart);
			
			// Product
			// Note:  will populate the id later on once get the information from db
			Product product = new Product();
			product.setName(dtoItem.getItemName());
			product.setPrice(dtoItem.getItemPrice());
			item.setProduct(product);
			
			// Quantity
			item.setQuantity(dtoItem.getNumOfItem());
			
			items.add(item);
		});
		objCart.setCartItems(items);
		
		return objCart;
	}
}
