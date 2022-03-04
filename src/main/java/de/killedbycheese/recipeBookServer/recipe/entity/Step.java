package de.killedbycheese.recipeBookServer.recipe.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class Step {
	@Positive(message = "stepNumber cannot be negative")
	private int stepNumber;
	@NotEmpty(message = "instruction cannot be missing")
	private String instruction;
	
	public Step() {
		// TODO Auto-generated constructor stub
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	
}
