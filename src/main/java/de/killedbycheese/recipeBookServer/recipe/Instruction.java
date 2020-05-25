package de.killedbycheese.recipeBookServer.recipe;


public class Instruction {
	
	private int instructionId;
	private int recipeId;
	private int index;
	private String step;
	
	
	public Instruction(int instructionId, int recipeId, int index, String step) {
		this.instructionId = instructionId;
		this.recipeId = recipeId;
		this.index = index;
		this.step = step;
	}
	
	
	public int getInstructionId() {
		return instructionId;
	}
	public int getRecipeId() {
		return recipeId;
	}
	public int getIndex() {
		return index;
	}
	public String getStep() {
		return step;
	}
	
	
}
