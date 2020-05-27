package de.killedbycheese.recipeBookServer.recipe.service;

import de.killedbycheese.recipeBookServer.recipe.util.Ingredient;
import de.killedbycheese.recipeBookServer.recipe.util.Instruction;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;
import de.killedbycheese.recipeBookServer.recipe.util.RecipeNotFoundException;

public class GetRecipeService {

	public static Recipe getRecipeByTitle(String recipeTitle) throws RecipeNotFoundException {
		
		//TODO build DB connection get recipe by provided title
		
		int idFromDB = 1337;
		String titleFromDB = "Salat";
		if(!recipeTitle.equals(titleFromDB)) {
			throw new RecipeNotFoundException("RecipeNotFound");
		}
		Recipe dummy = new Recipe(titleFromDB , idFromDB);
		Ingredient dummyIngredient1 = new Ingredient(420, 1337, "Tomatos", "500g");
		Ingredient dummyIngredient2 = new Ingredient(666, 1337, "Feldsalat", "200g");
		dummy.addIngredient(dummyIngredient1);
		dummy.addIngredient(dummyIngredient2);
		Instruction dummyInstruction1 = new Instruction(123, 1337, 1, "Tomaten waschen");
		Instruction dummyInstruction2 = new Instruction(99, 1337, 2, "Tomaten schneiden");
		Instruction dummyInstruction3 = new Instruction(44, 1337, 3, "Tomaten in den Salat mischen");
		dummy.addInstruction(dummyInstruction1);
		dummy.addInstruction(dummyInstruction2);
		dummy.addInstruction(dummyInstruction3);
		
		
		return dummy;
	}

}
