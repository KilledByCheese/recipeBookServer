package de.killedbycheese.recipeBookServer.recipe.service;


import java.util.List;
import java.util.Vector;

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

	public Vector<Recipe> getRecipeListByKeyword(String keyword, int page) throws RecipeNotFoundException {
		
		Vector<Recipe> recipeList = recipeDAO.getRecipeListByKeyword(keyword, page);
		
		return recipeList;
	}

	public Vector<Recipe> getRecipeListByCategoryList(List<String> categoryList, int page) throws RecipeNotFoundException {

		Vector<Recipe> recipeList = recipeDAO.getRecipeListByCategoryList(categoryList, page);
		
		return recipeList;
	}

	public Vector<Recipe> getRecipeList(int p) throws RecipeNotFoundException{
		
		Vector<Recipe> recipeList = recipeDAO.getRecipeList(p);
				
		return recipeList;
	}

}
