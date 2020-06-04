package de.killedbycheese.recipeBookServer.recipe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.killedbycheese.recipeBookServer.recipe.model.GetRecipeRequest;
import de.killedbycheese.recipeBookServer.recipe.model.GetRecipeResponse;
import de.killedbycheese.recipeBookServer.recipe.service.GetRecipeService;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;
import de.killedbycheese.recipeBookServer.recipe.util.RecipeNotFoundException;

@RestController
public class GetRecipeController {
	
	@Autowired
	private GetRecipeService getRecipeService;

	@GetMapping(value = "/getRecipeByTitle")
	public ResponseEntity<?> getRecipe(@Valid GetRecipeRequest recipeRequest) throws Exception {
		try {
			Recipe recipe;
			recipe = getRecipeService.getRecipeByTitle(recipeRequest.getRecipeTitle());
			return ResponseEntity.ok(new GetRecipeResponse(recipe));
		} catch (RecipeNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
}
