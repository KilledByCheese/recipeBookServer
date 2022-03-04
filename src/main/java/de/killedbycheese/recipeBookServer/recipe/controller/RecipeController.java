package de.killedbycheese.recipeBookServer.recipe.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.killedbycheese.recipeBookServer.recipe.dto.CreateRecipeRequest;
import de.killedbycheese.recipeBookServer.recipe.service.RecipeService;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService recipeService;

	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody CreateRecipeRequest newRecipe) throws Exception {
		recipeService.createRecipe(newRecipe);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
	    return new ErrorInfo(req.getRequestURI(), ex);
	}
}