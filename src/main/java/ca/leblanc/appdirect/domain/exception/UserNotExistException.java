package ca.leblanc.appdirect.domain.exception;

public class UserNotExistException extends Exception {

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public UserNotExistException(String id) {
		
		this.id = id;
	}

	public String getId() {
		return id;
	}	
}
