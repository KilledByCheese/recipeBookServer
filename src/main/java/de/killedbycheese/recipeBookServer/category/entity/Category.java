package de.killedbycheese.recipeBookServer.category.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;

@Document
public class Category {
	
	@Id
	private String categoryId;
	
	@Indexed(unique=true)
	private String value;
	
	@DocumentReference
	private List<Recipe> recipes = new ArrayList<Recipe>();

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	
}
