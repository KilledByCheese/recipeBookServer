package de.killedbycheese.recipeBookServer.util;

public class NotAuthorizedException extends Exception {

	private static final long serialVersionUID = 3561351160721671765L;
	
	public NotAuthorizedException(String message) {
		super(message);
	}

}
