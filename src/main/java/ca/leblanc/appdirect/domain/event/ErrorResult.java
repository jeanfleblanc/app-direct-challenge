package ca.leblanc.appdirect.domain.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "result")
public class ErrorResult implements Result {	
	
	/*
	 * This error code is typically used when AppDirect admins try to buy subscriptions for apps they have already purchased directly from the Application Vendor. In this scenario, we'll show users an error message and prompt them to link their accounts.
	 */
	public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
	
	/**
	 * This error code is typically used when AppDirect admins try to unassign users not found in the Application Vendor's account.
	 */
	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	
	/**
	 * This error code is typically used when AppDirect admins try to add or remove users from an account not found in the Application Vendor's records.
	 */
	public static final String	ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND"; 
	
	/**
	 * This error code is typically used when AppDirect admins try to assign users beyond the limit of the number of seats available. AppDirect will typically prevent that from happening by monitoring app usage.
	 */
	public static final String MAX_USERS_REACHED = "MAX_USERS_REACHED";
	
	/**
	 * This error code is returned when users try any action that is not authorized for that particular application. For example, if an application does not allow the original creator to be unassigned.
	 */
	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	
	/**
	 * This error code is returned when a user manually interrupts the operation (clicking cancel on the account creation page, etc.).
	 */
	public static final String OPERATION_CANCELED = "OPERATION_CANCELED"; 
	
	/**
	 * This error code is returned when the vendor endpoint is not currently configured.
	 */
	public static final String CONFIGURATION_ERROR = "CONFIGURATION_ERROR";
	
	/**
	 * This error code is returned when the vendor was unable to process the event fetched from AppDirect.
	 */
	public static final String INVALID_RESPONSE = "INVALID_RESPONSE"; 
	
	/**
	 * This error code may be used when none of the other error codes apply.
	 */
	public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";	
	
	private boolean success;
	private String errorCode;	
	private String message;
	
	/**
	 * Default constructor.
	 */
	public ErrorResult() { /** Do nothing */ }
	
	public ErrorResult(boolean success, String errorCode, String message) {
		
		this.success = success;
		this.errorCode = errorCode;		
		this.message = message;
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
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
