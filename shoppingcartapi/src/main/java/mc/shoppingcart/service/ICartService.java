package mc.shoppingcart.service;

import java.util.List;

import mc.shoppingcart.entity.Cart;

public interface ICartService {
	public List<Cart> getAllCarts();
	public Cart getCart(String name);
	
	public Cart createCart(final Cart requestedCart) throws IllegalArgumentException;
	public Cart updateCart(final Cart requestedCart) throws IllegalArgumentException;
	
	public Cart deleteItemsFromCart(final String cartName, final List<String> productNames) throws IllegalArgumentException;
}
