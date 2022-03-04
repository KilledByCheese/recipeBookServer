package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


public class IngredientDTO implements Serializable {

	private static final long serialVersionUID = -3610911866484938567L;

	@NotEmpty(message = "ingredient cannot be missing")
	private String ingredient;
	@Positive(message = "amount cannot be negative")
	private int amount;
	@NotEmpty(message = "unit cannot be missing")
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
