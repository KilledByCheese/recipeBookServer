package de.killedbycheese.recipeBookServer.recipe.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.recipe.util.Difficulty;

@Document
public class Recipe {
	
	@Id
	private String recipeId;
	
	private String title;
	private String subtitle;
	
	@DocumentReference
	private RecipeUser recipeUser; 
	
	@DocumentReference
	private List<Category> category = new ArrayList<Category>();
		
	private int serving;
	
	private Difficulty difficulty;
	
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	private List<Step> instructions = new ArrayList<Step>();
	
	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public RecipeUser getRecipeUser() {
		return recipeUser;
	}

	public void setRecipeUser(RecipeUser recipeUser) {
		this.recipeUser = recipeUser;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Step> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Step> instructions) {
		this.instructions = instructions;
	}

	
	
	
}
