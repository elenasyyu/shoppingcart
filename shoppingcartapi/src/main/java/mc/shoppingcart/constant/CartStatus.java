package mc.shoppingcart.constant;

import org.apache.commons.lang3.StringUtils;

public enum CartStatus {
	PROCESSING("PROCESSING"),
	CHECKOUT("CHECKOUT"),
	DONE("DONE")
	;
	
	private CartStatus(final String status) {
		this.status = status;
	}
	
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public final boolean isMatched(final String status) {
		if (StringUtils.isEmpty(status))
			return false;
		
		if (this.status.equals(status))
			return true;
		
		return false;
	}
}
