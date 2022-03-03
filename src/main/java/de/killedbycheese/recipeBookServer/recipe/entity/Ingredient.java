package de.killedbycheese.recipeBookServer.recipe.entity;


import de.killedbycheese.recipeBookServer.recipe.util.Unit;

public class Ingredient {
	
	private String ingredient;
	private int amount;
	
	private Unit unit;

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	

	
	
}
