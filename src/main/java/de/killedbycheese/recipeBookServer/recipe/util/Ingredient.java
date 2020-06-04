package de.killedbycheese.recipeBookServer.recipe.util;

import java.io.Serializable;

public class Ingredient implements Serializable {

	private static final long serialVersionUID = 8999549905178558759L;
	
	private long ingredientId;
	private long recipeId;
	private String ingredient;
	private String quantity;
	
	
	public Ingredient(long ingredientId, long recipeId, String ingredient, String quantity) {
		this.ingredientId = ingredientId;
		this.recipeId = recipeId;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	
	
	public long getIngredientId() {
		return ingredientId;
	}
	public long getRecipeId() {
		return recipeId;
	}
	public String getIngredient() {
		return ingredient;
	}
	public String getQuantity() {
		return quantity;
	}
	
	
	
}
