package mc.shoppingcart.dto;

import java.util.List;

public class ShoppingCartDetailDto {
	private String name;
	private List<ShoppingCartItemDto> items;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<ShoppingCartItemDto> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCartItemDto> items) {
		this.items = items;
	}
}
