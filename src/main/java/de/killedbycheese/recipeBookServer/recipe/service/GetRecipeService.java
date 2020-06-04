package de.killedbycheese.recipeBookServer.recipe.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.recipe.service.dao.RecipeDAO;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;
import de.killedbycheese.recipeBookServer.recipe.util.RecipeNotFoundException;

@Service
public class GetRecipeService {
	
	@Autowired
	private RecipeDAO recipeDAO;

	public Recipe getRecipeByTitle(String recipeTitle) throws RecipeNotFoundException {
		
		Recipe recipe = recipeDAO.getRecipeByTitle(recipeTitle);
		
		return recipe;
		
	}

}
