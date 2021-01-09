package mc.shoppingcart.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ShoppingCartErrors {
	private HttpStatus status;
	private String message;
	private Integer count;
	private List<ExceptionInfo> data;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public List<ExceptionInfo> getData() {
		return data;
	}
	public void setData(List<ExceptionInfo> data) {
		this.data = data;
	}
}
