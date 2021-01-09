package mc.shoppingcart.exception;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartException extends Exception {
	private static final long serialVersionUID = 2043727344296040168L;

	public ShoppingCartException() {
		super ("Shopping Cart Exception");
	}
	
	private List<ExceptionInfo> exceptionList = new ArrayList<ExceptionInfo>();
	
	public void AddException(final ExceptionInfo exceptionInfo) {
		if (exceptionInfo == null) 
			return;
		
		exceptionList.add(exceptionInfo);
	}

	public List<ExceptionInfo> getExceptionList() {
		return exceptionList;
	}
}
