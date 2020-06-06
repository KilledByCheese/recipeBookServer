package de.killedbycheese.recipeBookServer.recipe.model.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class GetRecipeListByCategoryListRequest implements Serializable {

	private static final long serialVersionUID = 7688584521268085711L;
	
	@NotNull
	private List<String> categoryList;

	private int page = 1;
	
	//Default for Json
	public GetRecipeListByCategoryListRequest() {
	}

	public GetRecipeListByCategoryListRequest(List<String> categoryList, int page) {
		this.categoryList = categoryList;
		this.page = page;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
