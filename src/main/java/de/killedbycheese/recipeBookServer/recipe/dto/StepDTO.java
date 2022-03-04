package de.killedbycheese.recipeBookServer.recipe.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class StepDTO implements Serializable {

	private static final long serialVersionUID = 8054422048288864691L;

	@Positive(message = "stepNumber cannot be negative")
	private int stepNumber;
	@NotEmpty(message = "instruction cannot be missing")
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
