package mc.shoppingcart.dto;

/*
 * Shopping Card Item Data Transfer Object
 */
public class ShoppingCartItemDto {
	private String itemName;
	private double itemPrice;
	private int numOfItem;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public int getNumOfItem() {
		return numOfItem;
	}
	public void setNumOfItem(int numOfItem) {
		this.numOfItem = numOfItem;
	}
}
