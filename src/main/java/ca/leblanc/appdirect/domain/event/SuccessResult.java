package ca.leblanc.appdirect.domain.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "result")
public class SuccessResult implements Result {	
	
	public static final String ACCOUNT_CREATION_SUCCESSFUL = "Account creation successful";
	public static final String ACCOUNT_CHANGE_SUCCESSFUL = "Account change successfull";
	public static final String ACCOUNT_CANCELLATION_SUCCESSFUL = "Account cancellation successful";
	public static final String ACCOUNT_ADDON_SUCCESSFUL = "Account Addon successfull";
	public static final String ASSIGN_USER_SUCCESSFUL = "Assign user successfull";
	public static final String UNASSIGN_USER_SUCCESSFUL = "Unassign user successfull";
	public static final String STATUS_NOTIFICATION_SUCCESSFUL = "Status Notification Successfull";
	
	private boolean success;
	private String message;
	private String accountIdentifier;
	
	/**
	 * Default constructor.
	 */
	public SuccessResult() { /** Do nothing */ }

	public SuccessResult(boolean success, String message) {
		
		this.success = success;
		this.message = message;
	}	
	
	public SuccessResult(boolean success, String message, String accountIdentifier) {
		
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
