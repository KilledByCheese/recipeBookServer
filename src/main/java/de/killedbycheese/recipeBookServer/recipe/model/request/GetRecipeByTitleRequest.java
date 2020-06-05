package de.killedbycheese.recipeBookServer.recipe.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetRecipeByTitleRequest implements Serializable {

	private static final long serialVersionUID = -8038588923440323929L;
	
	@NotNull
	private String recipeTitle ="";

	//Default for Json
	public GetRecipeByTitleRequest() {
	}
	
	public GetRecipeByTitleRequest(String title) {
		this.setRecipeTitle(title);
	}

	public void setRecipeTitle(String title) {
		this.recipeTitle = title;
	}
	
	public String getRecipeTitle() {
		return recipeTitle;
	}

}
