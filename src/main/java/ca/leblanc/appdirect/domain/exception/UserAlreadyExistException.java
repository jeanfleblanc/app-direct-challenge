package ca.leblanc.appdirect.domain.exception;

public class UserAlreadyExistException extends Exception {

	/**
	 * Default UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	public UserAlreadyExistException(String id) {
		
		this.id = id;
	}

	public String getId() {
		return id;
	}	
}
