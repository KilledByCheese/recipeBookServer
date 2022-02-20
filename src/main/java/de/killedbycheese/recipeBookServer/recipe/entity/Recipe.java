package de.killedbycheese.recipeBookServer.recipe.entity;

import java.util.List;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.recipe.util.Difficulty;

public class Recipe {

	private Long id;
	private String title;
	private String subtitle;
	private RecipeUser owner; 
	private List<Category> category;
	private int serving;
	private Difficulty difficulty;
	private List<Ingredient> ingredients;
	private List<Step> instructions;
}
