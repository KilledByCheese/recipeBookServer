package de.killedbycheese.recipeBookServer.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
import de.killedbycheese.recipeBookServer.auth.repository.RecipeUserRepository;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.category.repository.CategoryRepository;
import de.killedbycheese.recipeBookServer.recipe.dto.CreateRecipeRequest;
import de.killedbycheese.recipeBookServer.recipe.dto.IngredientDTO;
import de.killedbycheese.recipeBookServer.recipe.dto.StepDTO;
import de.killedbycheese.recipeBookServer.recipe.entity.Ingredient;
import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;
import de.killedbycheese.recipeBookServer.recipe.entity.Step;
import de.killedbycheese.recipeBookServer.recipe.repository.RecipeRepository;
import de.killedbycheese.recipeBookServer.recipe.util.Difficulty;
import de.killedbycheese.recipeBookServer.recipe.util.Unit;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RecipeUserRepository recipeUserRepository;

	@Transactional
	public void createRecipe(CreateRecipeRequest newRecipe, String username) {
		Recipe recipe = new Recipe();
		recipe.setTitle(newRecipe.getTitle());
		recipe.setSubtitle(newRecipe.getSubtitle());
		recipe.setServing(newRecipe.getServing());
		
		for(String c : newRecipe.getCategories()) {
			Category findByValue = categoryRepository.findByValue(c);
			recipe.getCategory().add(findByValue);
		}
		
		recipe.setDifficulty(Difficulty.valueOf(newRecipe.getDifficulty()));
		
		for(IngredientDTO i : newRecipe.getIngredients()) {
			Ingredient ingr = new Ingredient();
			ingr.setAmount(i.getAmount());
			ingr.setIngredient(i.getIngredient());
			ingr.setUnit(Unit.valueOf(i.getUnit()));
			recipe.getIngredients().add(ingr);
		}
		
		for(StepDTO s : newRecipe.getInstructions()) {
			Step step = new Step();
			step.setInstruction(s.getInstruction());
			step.setStepNumber(s.getStepNumber());;
			recipe.getInstructions().add(step);
		}
		RecipeUser user = recipeUserRepository.findByUserName(username);
		recipe.setRecipeUser(user);
		recipeRepository.save(recipe);
		
	}

}
