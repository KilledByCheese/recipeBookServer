package de.killedbycheese.recipeBookServer.recipe.controller;


import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.killedbycheese.recipeBookServer.recipe.dto.RecipeDTO;
import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;
import de.killedbycheese.recipeBookServer.recipe.service.RecipeService;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;
import de.killedbycheese.recipeBookServer.util.NotAuthorizedException;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService recipeService;

	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody RecipeDTO newRecipe) throws Exception {
		recipeService.createRecipe(newRecipe);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@GetMapping("/{recipeID}")
	public ResponseEntity<?> getRecipeById(@NotEmpty @PathVariable String recipeID) throws Exception {
		Recipe recipe = recipeService.getRecipe(recipeID);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);			
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRecipes() throws Exception {
		List<Recipe> allRecipes = recipeService.getAllRecipes();
		return new ResponseEntity<List<Recipe>>(allRecipes, HttpStatus.OK);			
	}
	
	@PutMapping
	public ResponseEntity<?> updateRecipe(@Valid @RequestBody RecipeDTO updateRecipe) throws Exception {
		recipeService.updateRecipe(updateRecipe);
		return new ResponseEntity<String>("Success", HttpStatus.OK);			
	}
	
	@DeleteMapping("/{recipeID}")
	public ResponseEntity<?> deleteRecipe(@NotEmpty @PathVariable String recipeID) throws Exception {
		recipeService.deleteRecipe(recipeID);
		return new ResponseEntity<String>("Success", HttpStatus.OK);			
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
	    return new ErrorInfo(req.getRequestURI(), ex);
	}
	
	@ExceptionHandler({NoSuchElementException.class})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody ErrorInfo
	handleNotFoundException(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
		return new ErrorInfo(req.getRequestURI(), ex);
	}
	
	@ExceptionHandler({NotAuthorizedException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody ResponseEntity<String>
	handleUnauthorized(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
		return new ResponseEntity<String>("Unauthorized",HttpStatus.UNAUTHORIZED);
	} 
}
