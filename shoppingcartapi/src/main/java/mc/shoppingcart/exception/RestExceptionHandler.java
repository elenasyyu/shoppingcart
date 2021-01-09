package mc.shoppingcart.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private final String INTERNAL_ERR_MSG = "Internal Server Error - Contact Your System Administrator";
	
	@ExceptionHandler(ShoppingCartException.class)
	protected ResponseEntity<Object> handleShoppingCartException(ShoppingCartException scException) {
		ShoppingCartErrors errors = new ShoppingCartErrors();
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errors.setMessage(INTERNAL_ERR_MSG);
		errors.setData(scException.getExceptionList());
		errors.setCount(scException.getExceptionList().size());
		
		return new ResponseEntity<Object>(errors, errors.getStatus());
	}
}
