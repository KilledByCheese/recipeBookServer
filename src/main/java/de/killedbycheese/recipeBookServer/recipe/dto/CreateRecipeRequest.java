package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateRecipeRequest implements Serializable {

	private static final long serialVersionUID = 7120687813120152583L;

	private String title;
	private String subtitle;
	
	private List<String> categories = new ArrayList<String>();

	private int serving;
	private String difficulty;
	
	private List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
	private List<StepDTO> instructions = new ArrayList<StepDTO>();
	
	public CreateRecipeRequest() {
		// TODO Auto-generated constructor stub
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
