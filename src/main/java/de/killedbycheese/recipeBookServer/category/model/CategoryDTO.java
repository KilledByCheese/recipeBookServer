package de.killedbycheese.recipeBookServer.category.model;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = -5213891331889088167L;

	private int categoryid;
	private String category;
	
	public CategoryDTO() {
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
