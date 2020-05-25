package de.killedbycheese.recipeBookServer.recipe;


public class Ingredient {

	private int ingredientId;
	private int recipeId;
	private String ingredient;
	private String quantity;
	
	
	public Ingredient(int ingredientId, int recipeId, String ingredient, String quantity) {
		this.ingredientId = ingredientId;
		this.recipeId = recipeId;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	
	
	public int getIngredientId() {
		return ingredientId;
	}
	public int getRecipeId() {
		return recipeId;
	}
	public String getIngredient() {
		return ingredient;
	}
	public String getQuantity() {
		return quantity;
	}
	
	
	
}
