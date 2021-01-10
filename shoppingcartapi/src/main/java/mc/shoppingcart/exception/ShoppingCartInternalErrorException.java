package mc.shoppingcart.exception;

public class ShoppingCartInternalErrorException extends Exception {
	private static final long serialVersionUID = 5108903326110390562L;

	public ShoppingCartInternalErrorException(String message) {
		super(message);
	}
	
	public ShoppingCartInternalErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}