package mc.shoppingcart.dto;

/*
 * Shopping Card Item Data Transfer Object
 */
public class ShoppingCardItemDto {
	private String itemName;
	private long itemPrice;
	private int numOfItem;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(long itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public int getNumOfItem() {
		return numOfItem;
	}
	public void setNumOfItem(int numOfItem) {
		this.numOfItem = numOfItem;
	}
}
