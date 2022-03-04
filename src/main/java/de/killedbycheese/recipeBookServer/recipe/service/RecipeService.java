package de.killedbycheese.recipeBookServer.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
import de.killedbycheese.recipeBookServer.auth.repository.RecipeUserRepository;
import de.killedbycheese.recipeBookServer.auth.util.UserRole;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.category.repository.CategoryRepository;
import de.killedbycheese.recipeBookServer.recipe.dto.RecipeDTO;
import de.killedbycheese.recipeBookServer.recipe.dto.IngredientDTO;
import de.killedbycheese.recipeBookServer.recipe.dto.StepDTO;
import de.killedbycheese.recipeBookServer.recipe.entity.Ingredient;
import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;
import de.killedbycheese.recipeBookServer.recipe.entity.Step;
import de.killedbycheese.recipeBookServer.recipe.repository.RecipeRepository;
import de.killedbycheese.recipeBookServer.recipe.util.Difficulty;
import de.killedbycheese.recipeBookServer.recipe.util.Unit;
import de.killedbycheese.recipeBookServer.util.NotAuthorizedException;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RecipeUserRepository recipeUserRepository;

	@Transactional
	public void createRecipe(@Valid RecipeDTO newRecipe) {
		Recipe recipe = new Recipe();
		recipe.setTitle(newRecipe.getTitle());
		recipe.setSubtitle(newRecipe.getSubtitle());
		recipe.setServing(newRecipe.getServing());
		
		for(String c : newRecipe.getCategories()) {
			Category findByValue = categoryRepository.findByValue(c.toUpperCase());
			if(findByValue != null) recipe.getCategory().add(findByValue);
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
		RecipeUser user = recipeUserRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		recipe.setRecipeUser(user);
		recipeRepository.save(recipe);
		
	}
	
	public Recipe getRecipe(String id) {
		return recipeRepository.findById(id).orElseThrow();
	}
	
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	@Transactional
	public void updateRecipe(@Valid RecipeDTO updateRecipe) throws NotAuthorizedException {
		if(updateRecipe.getRecipeId() == null) throw new NoSuchElementException("RecipeID cannot be missing on update");
		
		Recipe recipe = recipeRepository.findById(updateRecipe.getRecipeId()).orElseThrow();
		RecipeUser user = recipeUserRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(!(recipe.getRecipeUser().getRecipeUserId().equals(user.getRecipeUserId()))) {
			if(!user.getUserRole().equals(UserRole.ADMIN)) {
				throw new NotAuthorizedException("Unable to update Recipe from other user");
			}
		}
		recipe.setTitle(updateRecipe.getTitle());
		recipe.setSubtitle(updateRecipe.getSubtitle());
		recipe.setServing(updateRecipe.getServing());
		
		List<Category> categories = new ArrayList<Category>();
		for(String c : updateRecipe.getCategories()) {
			Category findByValue = categoryRepository.findByValue(c.toUpperCase());
			if(findByValue != null) categories.add(findByValue);
		}
		recipe.setCategory(categories);
		
		recipe.setDifficulty(Difficulty.valueOf(updateRecipe.getDifficulty()));
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for(IngredientDTO i : updateRecipe.getIngredients()) {
			Ingredient ingr = new Ingredient();
			ingr.setAmount(i.getAmount());
			ingr.setIngredient(i.getIngredient());
			ingr.setUnit(Unit.valueOf(i.getUnit()));
			ingredients.add(ingr);
		}
		recipe.setIngredients(ingredients);
		
		List<Step> steps = new ArrayList<Step>();
		for(StepDTO s : updateRecipe.getInstructions()) {
			Step step = new Step();
			step.setInstruction(s.getInstruction());
			step.setStepNumber(s.getStepNumber());;
			steps.add(step);
		}
		recipe.setInstructions(steps);
		
		recipeRepository.save(recipe);
	}
	
	public void deleteRecipe(@NotEmpty String recipeId) throws NotAuthorizedException {
		Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
		RecipeUser user = recipeUserRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		if(!(recipe.getRecipeUser().getRecipeUserId().equals(user.getRecipeUserId()))) {
			if(!user.getUserRole().equals(UserRole.ADMIN)) {
				throw new NotAuthorizedException("Unable to delete Recipe from other user");
			}
		}
		recipeRepository.delete(recipe);
	}

}
