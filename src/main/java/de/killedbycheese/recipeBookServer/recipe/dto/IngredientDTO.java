package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;


public class IngredientDTO implements Serializable {

	private static final long serialVersionUID = -3610911866484938567L;

	private String ingredient;
	private int amount;
	private String unit;
	
	public IngredientDTO() {
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
