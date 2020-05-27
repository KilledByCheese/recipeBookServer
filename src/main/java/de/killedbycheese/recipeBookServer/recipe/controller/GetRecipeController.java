package de.killedbycheese.recipeBookServer.recipe.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.killedbycheese.recipeBookServer.recipe.model.GetRecipeRequest;
import de.killedbycheese.recipeBookServer.recipe.model.GetRecipeResponse;
import de.killedbycheese.recipeBookServer.recipe.service.GetRecipeService;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;

@RestController
public class GetRecipeController {

	@RequestMapping(value="/getRecipeByTitle", method=RequestMethod.GET)
	public ResponseEntity<?> getRecipe(@Valid GetRecipeRequest recipeRequest ) throws Exception{
		
		
		final Recipe recipe = GetRecipeService.getRecipeByTitle(recipeRequest.getRecipeTitle());
		return ResponseEntity.ok(new GetRecipeResponse(recipe));
	}
}
