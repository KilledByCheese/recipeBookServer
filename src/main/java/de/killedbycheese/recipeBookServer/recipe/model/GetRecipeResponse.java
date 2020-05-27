package de.killedbycheese.recipeBookServer.recipe.model;

import java.io.Serializable;

import de.killedbycheese.recipeBookServer.recipe.util.Recipe;

public class GetRecipeResponse implements Serializable {

	private static final long serialVersionUID = 6530266945188153899L;

	private final Recipe recipe;
	
	public GetRecipeResponse(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public Recipe getRecipe() {
		return this.recipe;
	}
	
	
}
