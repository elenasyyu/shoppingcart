package mc.shoppingcart.util;

import mc.shoppingcart.dto.ProductDto;
import mc.shoppingcart.entity.Product;

public class DtoConverter {
	public static ProductDto convertProductToDto(final Product product) {
		ProductDto dto = new ProductDto();
		
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		
		return dto;
	}
}
