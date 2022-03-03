package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;

public class StepDTO implements Serializable {

	private static final long serialVersionUID = 8054422048288864691L;

	private int stepNumber;
	private String instruction;
	
	public StepDTO() {
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
