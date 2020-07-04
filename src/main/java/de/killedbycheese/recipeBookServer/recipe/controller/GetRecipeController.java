package de.killedbycheese.recipeBookServer.recipe.controller;

import java.util.List;
import java.util.Vector;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.killedbycheese.recipeBookServer.recipe.model.request.GetRecipeByTitleRequest;
import de.killedbycheese.recipeBookServer.recipe.model.request.GetRecipeListByCategoryListRequest;
import de.killedbycheese.recipeBookServer.recipe.model.request.GetRecipeListByKeywordRequest;
import de.killedbycheese.recipeBookServer.recipe.model.response.RecipeListResponse;
import de.killedbycheese.recipeBookServer.recipe.model.response.RecipeResponse;
import de.killedbycheese.recipeBookServer.recipe.service.GetRecipeService;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;
import de.killedbycheese.recipeBookServer.recipe.util.RecipeNotFoundException;


@RestController
public class GetRecipeController {
	
	@Autowired
	private GetRecipeService getRecipeService;
	
	@RequestMapping(value= "/isTokenValid", method = RequestMethod.POST)
	public ResponseEntity<?> validateToken() throws Exception {
		return ResponseEntity.ok("Token still valid");
	}

	@GetMapping(value = "/getRecipes")
	public ResponseEntity<?> getRecipeList(@RequestParam(value = "page", defaultValue = "1") Integer page) {
		try {
			int p = page;
			Vector<Recipe> recipeList = getRecipeService.getRecipeList(p);			
			return ResponseEntity.ok(new RecipeListResponse(recipeList));
			
		} catch (RecipeNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "/getRecipeByTitle")
	public ResponseEntity<?> getRecipeByTitle(@Valid GetRecipeByTitleRequest recipeRequest) throws Exception {
		try {
			String recipeTitle = recipeRequest.getRecipeTitle();
			if(recipeTitle.equals("")) throw new RecipeNotFoundException("No Title Provided");
			Recipe recipe;
			recipe = getRecipeService.getRecipeByTitle(recipeTitle);
			return ResponseEntity.ok(new RecipeResponse(recipe));
		} catch (RecipeNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping(value = "/getRecipeListByKeyword")
	public ResponseEntity<?> getRecipeListByKeyword(@Valid GetRecipeListByKeywordRequest keywordRequest) throws Exception {
		try {
			String keyword = keywordRequest.getKeyword();
			if(keyword.equals("")) throw new RecipeNotFoundException("No Keyword Provided");
			int page = keywordRequest.getPage();
			if(page <= 0) throw new RecipeNotFoundException("Page not Valid");
			
			Vector<Recipe> recipeList;
			
			recipeList = getRecipeService.getRecipeListByKeyword(keyword, page);		
			
			return ResponseEntity.ok(new RecipeListResponse(recipeList));
		} catch (RecipeNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getRecipeListByCategoryList", method = RequestMethod.POST)
	public ResponseEntity<?> getRecipeListByCategoryList(@Valid @RequestBody GetRecipeListByCategoryListRequest categoryRequest) throws Exception {
		try {
			List<String> categoryList = categoryRequest.getCategoryList();
			if (categoryList.isEmpty() || categoryList == null)
				throw new RecipeNotFoundException("No Categorys provided");
			int page = categoryRequest.getPage();
			if (page <= 0)
				throw new RecipeNotFoundException("Page not Valid");
			
			Vector<Recipe> recipeList;
			
			recipeList = getRecipeService.getRecipeListByCategoryList(categoryList, page);
			
			return ResponseEntity.ok(new RecipeListResponse(recipeList));
			
		} catch (RecipeNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
		
		
	}
}
