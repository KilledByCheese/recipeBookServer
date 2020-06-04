package de.killedbycheese.recipeBookServer.recipe.util;

import java.io.Serializable;

public class Instruction implements Serializable {
	
	private static final long serialVersionUID = -7322110629329639058L;
	
	private long instructionId;
	private long recipeId;
	private int index;
	private String step;
	
	
	public Instruction(long instructionId, long recipeId, int index, String step) {
		this.instructionId = instructionId;
		this.recipeId = recipeId;
		this.index = index;
		this.step = step;
	}
	
	
	public long getInstructionId() {
		return instructionId;
	}
	public long getRecipeId() {
		return recipeId;
	}
	public int getIndex() {
		return index;
	}
	public String getStep() {
		return step;
	}
	
	
}
