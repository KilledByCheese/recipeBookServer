package de.killedbycheese.recipeBookServer.recipe.model.response;

import java.io.Serializable;
import java.util.Vector;

import de.killedbycheese.recipeBookServer.recipe.util.Recipe;

public class RecipeListResponse implements Serializable {

	private static final long serialVersionUID = -8127557479849950934L;
	
	private final Vector<Recipe> recipeList;
	
	public RecipeListResponse(Vector<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	
	public Vector<Recipe> getRecipeList() {
		return this.recipeList;
	}

}
