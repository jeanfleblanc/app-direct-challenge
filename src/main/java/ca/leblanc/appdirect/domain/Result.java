package ca.leblanc.appdirect.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "userType")
@XmlRootElement(name = "Result")
public class Result {

	private boolean success;
	private String message;
	private String accountIdentifier;
	
	/**
	 * Default constructor.
	 */
	public Result() { /** Do nothing */ }
	
	public Result(boolean success, String message, String accountIdentifier) {
		
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
