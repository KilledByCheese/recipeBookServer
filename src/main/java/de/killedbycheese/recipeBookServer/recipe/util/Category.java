package de.killedbycheese.recipeBookServer.recipe.util;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 2933336518523285486L;
	
	private long recipeID;
	private long categoryID;
	private String category;
	
	public Category(long recipeID, long categoryID, String category) {
		super();
		this.recipeID = recipeID;
		this.categoryID = categoryID;
		this.category = category;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public long getRecipeID() {
		return recipeID;
	}
	
	public long getCategoryID() {
		return categoryID;
	}
	
	public String getCategory() {
		return category;
	}
	

}
