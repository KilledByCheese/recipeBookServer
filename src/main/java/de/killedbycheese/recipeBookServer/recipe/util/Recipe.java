package de.killedbycheese.recipeBookServer.recipe.util;

import java.util.Vector;

public class Recipe {
	
	private String title;
	private int id;
	
	private Vector<Ingredient> ingredientList;
	private Vector<Instruction> instructionList;
	
	
	public Recipe(String title, int id) {
		
		this.title = title;
		this.id = id;
		
		this.ingredientList = new Vector<>();
		this.instructionList = new Vector<>();
		
	}
	
	public int getId() {
		return id;
	}

	public Vector<Ingredient> getIngridentList() {
		return ingredientList;
	}


	public Vector<Instruction> getInstructionList() {
		return instructionList;
	}
	
	public String getTitle() {
		return title;
	}
	
	
	/**
	 * 
	 * @param ingredient
	 * @return false if the provided recipeId is not the same otherwise true
	 */
	public boolean addIngredient(Ingredient ingredient) {
		if(ingredient.getRecipeId() != id) return false;
		ingredientList.add(ingredient);
		return true;
	}
	
	/**
	 * 
	 * @param instruction
	 * @return false if the recipeId is not the same or the provided index is already present otherwise true
	 */
	public boolean addInstruction(Instruction instruction) {
		if(instruction.getRecipeId() != id) return false;
		for (Instruction inst : instructionList) {
			if(inst.getIndex() == instruction.getIndex()) return false;
		}
		instructionList.add(instruction);
		return true;
	}
}
