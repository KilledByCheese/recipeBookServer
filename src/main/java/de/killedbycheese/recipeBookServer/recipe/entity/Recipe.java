package de.killedbycheese.recipeBookServer.recipe.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	@NotEmpty(message = "title cannot be missing")
	private String title;
	@NotEmpty(message = "subtitle cannot be missing")
	private String subtitle;
	@NotNull(message = "ingredient cannot be missing")
	@DocumentReference
	private RecipeUser recipeUser; 
	@DocumentReference
	@NotEmpty(message = "category cannot be empty")
	private List<Category> category = new ArrayList<Category>();
	@Positive(message = "serving cannot be negative")
	private int serving;
	@NotEmpty(message = "difficulty cannot be missing")
	private Difficulty difficulty;
	@Valid
	@NotEmpty(message = "ingredients cannot be empty")
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	@Valid
	@NotEmpty(message = "instructions cannot be empty")
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
