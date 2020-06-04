package de.killedbycheese.recipeBookServer.recipe.util;

import java.io.Serializable;
import java.util.Vector;

public class Recipe implements Serializable {
	
	private static final long serialVersionUID = -5582035696192226709L;
	
	
	private String title;
	private long id;
	private String serves;
	
	private Vector<Ingredient> ingredientList;
	private Vector<Instruction> instructionList;
	private Vector<PictureURL> pictureList;
	private Vector<Category> categoryList;
	
	
	public Recipe(String title, long id, String serves) {
		
		this.title = title;
		this.id = id;
		this.serves = serves;
		
		this.ingredientList = new Vector<>();
		this.instructionList = new Vector<>();
		this.pictureList = new Vector<>();
		this.categoryList = new Vector<>();
		
	}
	
	public long getId() {
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
	
	public String getServes() {
		return serves;
	}	
	
	public Vector<PictureURL> getPictureList() {
		return pictureList;
	}

	public Vector<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * param ingredient
	 * return false if the provided recipeId is not the same otherwise true
	 */
	public boolean addIngredient(Ingredient ingredient) {
		if(ingredient.getRecipeId() != id) return false;
		ingredientList.add(ingredient);
		return true;
	}
	
	/**
	 * 
	 * param instruction
	 * return false if the recipeId is not the same or the provided index is already present otherwise true
	 */
	public boolean addInstruction(Instruction instruction) {
		if(instruction.getRecipeId() != id) return false;
		for (Instruction inst : instructionList) {
			if(inst.getIndex() == instruction.getIndex()) return false;
		}
		instructionList.add(instruction);
		return true;
	}
	
	public boolean addPictureURL(PictureURL url) {
		if(url.getRecipeID() != id) return false;
		for (PictureURL pic : pictureList) {
			if(pic.getPictureURL().equals(url.getPictureURL())) return false;
		}
		pictureList.add(url);
		return true;
	}
	
	public boolean addCategory(Category category) {
		if(category.getRecipeID() != id) return false;
		for(Category cat : categoryList) {
			if(cat.getCategory().equals(category.getCategory())) return false;
		}
		categoryList.add(category);
		return true;
	}

	
}
