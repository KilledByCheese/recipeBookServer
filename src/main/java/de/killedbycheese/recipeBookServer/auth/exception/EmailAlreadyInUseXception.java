package de.killedbycheese.recipeBookServer.auth.exception;

public class EmailAlreadyInUseXception extends Exception {

	private static final long serialVersionUID = -6671496800014980171L;
	
	public EmailAlreadyInUseXception(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public EmailAlreadyInUseXception(String errorMessage) {
		super(errorMessage);
	}
	public EmailAlreadyInUseXception() {
		super();
	}

}
