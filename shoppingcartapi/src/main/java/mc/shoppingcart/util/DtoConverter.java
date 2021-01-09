package mc.shoppingcart.util;

import java.util.ArrayList;
import java.util.List;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.dto.ShoppingCartDetailDto;
import mc.shoppingcart.dto.ShoppingCartItemDto;
import mc.shoppingcart.entity.Cart;
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
}
