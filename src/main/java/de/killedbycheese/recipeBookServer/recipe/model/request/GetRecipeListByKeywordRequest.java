package de.killedbycheese.recipeBookServer.recipe.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetRecipeListByKeywordRequest implements Serializable {

	private static final long serialVersionUID = -5414501170430481335L;

	@NotNull
	private String keyword = "";
	
	@NotNull
	private int page = 1;

	//Default for Json
	public GetRecipeListByKeywordRequest() {
	}
	
	public GetRecipeListByKeywordRequest(String keyword, int page) {
		this.keyword = keyword;
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	

}
