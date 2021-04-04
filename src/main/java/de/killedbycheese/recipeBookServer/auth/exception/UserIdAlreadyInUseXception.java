package de.killedbycheese.recipeBookServer.auth.exception;

public class UserIdAlreadyInUseXception extends Exception {

	private static final long serialVersionUID = -3602658336589976836L;
	
	public UserIdAlreadyInUseXception(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public UserIdAlreadyInUseXception(String errorMessage) {
		super(errorMessage);
	}
	public UserIdAlreadyInUseXception() {
		super();
	}

}
