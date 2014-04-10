package ca.leblanc.appdirect.domain.exception;

public class SubscriptionNotFoundException extends Exception {

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public SubscriptionNotFoundException(String id) {
		
		this.id = id;
	}

	public String getId() {
		return id;
	}	
}
