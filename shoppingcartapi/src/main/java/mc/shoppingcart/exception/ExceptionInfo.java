package mc.shoppingcart.exception;

public class ExceptionInfo {
	private String id;
	private String debugMessage;
	
	public ExceptionInfo() {
	}
	
	public ExceptionInfo(final String id, final String debugMessage) {
		this.id = id;
		this.debugMessage = debugMessage;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
}
