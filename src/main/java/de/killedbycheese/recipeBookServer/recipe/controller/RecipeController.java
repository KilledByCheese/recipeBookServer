package de.killedbycheese.recipeBookServer.recipe.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import de.killedbycheese.recipeBookServer.auth.service.JwtUserDetailsService;
import de.killedbycheese.recipeBookServer.auth.util.JwtTokenUtil;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.recipe.dto.CreateRecipeRequest;
import de.killedbycheese.recipeBookServer.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping
	public ResponseEntity<?> createCategory(@RequestHeader(name="Authorization") String token,@Valid @RequestBody CreateRecipeRequest newRecipe) throws Exception {
		if (token != null && token.startsWith("Bearer ")) {
			String jwtToken = token.substring(7);
			String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			
			
			recipeService.createRecipe(newRecipe, username);
			return new ResponseEntity<String>("Success", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("No User Provided", HttpStatus.BAD_REQUEST);
		
	}
}
