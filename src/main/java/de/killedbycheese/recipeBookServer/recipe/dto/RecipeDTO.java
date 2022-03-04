package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class RecipeDTO implements Serializable {

	private static final long serialVersionUID = 7120687813120152583L;
	
	private String recipeId;

	@NotEmpty(message = "title cannot be missing")
	private String title;
	@NotEmpty(message = "subtitle cannot be missing")
	private String subtitle;
	
	@NotEmpty(message = "categories cannot be empty")
	private List<String> categories = new ArrayList<String>();

	private int serving;
	@NotEmpty(message = "difficulty cannot be missing")
	private String difficulty;
	@Valid
	@NotEmpty(message = "ingredients cannot be empty")
	private List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
	@Valid
	@NotEmpty(message = "instructions cannot be empty")
	private List<StepDTO> instructions = new ArrayList<StepDTO>();
	
	public RecipeDTO() {
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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public List<StepDTO> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<StepDTO> instructions) {
		this.instructions = instructions;
	}
	
}
