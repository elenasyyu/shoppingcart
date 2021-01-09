package mc.shoppingcart.dto;

import java.util.List;

public class ShoppingCardDetailDto {
	private String name;
	private List<ShoppingCardItemDto> items;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<ShoppingCardItemDto> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCardItemDto> items) {
		this.items = items;
	}
}
