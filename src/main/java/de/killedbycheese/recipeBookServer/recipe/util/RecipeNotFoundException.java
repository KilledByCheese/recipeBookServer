package de.killedbycheese.recipeBookServer.recipe.util;

public class RecipeNotFoundException extends Exception {

	private static final long serialVersionUID = 7656317651909880251L;

	public RecipeNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public RecipeNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
