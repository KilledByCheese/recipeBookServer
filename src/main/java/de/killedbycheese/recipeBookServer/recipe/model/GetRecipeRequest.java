package de.killedbycheese.recipeBookServer.recipe.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetRecipeRequest implements Serializable {

	private static final long serialVersionUID = -8038588923440323929L;
	
	@NotNull
	private String recipeTitle ="";

	//Default for Json
	public GetRecipeRequest() {
	}
	
	public GetRecipeRequest(String title) {
		this.setRecipeTitle(title);
	}

	public void setRecipeTitle(String title) {
		this.recipeTitle = title;
	}
	
	public String getRecipeTitle() {
		return recipeTitle;
	}

}
