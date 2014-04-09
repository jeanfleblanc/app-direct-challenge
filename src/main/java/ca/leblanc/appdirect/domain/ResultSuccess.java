package ca.leblanc.appdirect.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "result")
public class ResultSuccess implements Result {	
	
	public static final String ACCOUNT_CREATION_SUCCESSFUL = "Account creation successful";
	
	private boolean success;
	private String message;
	private String accountIdentifier;
	
	/**
	 * Default constructor.
	 */
	public ResultSuccess() { /** Do nothing */ }
	
	public ResultSuccess(boolean success, String message, String accountIdentifier) {
		
		this.success = success;
		this.message = message;
		this.accountIdentifier = accountIdentifier;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
}
