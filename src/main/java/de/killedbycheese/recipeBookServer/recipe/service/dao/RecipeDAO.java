package de.killedbycheese.recipeBookServer.recipe.service.dao;

import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.recipe.service.mapper.RecipeTitleMapper;
import de.killedbycheese.recipeBookServer.recipe.service.mapper.RecipeRowMapper;
import de.killedbycheese.recipeBookServer.recipe.util.Category;
import de.killedbycheese.recipeBookServer.recipe.util.Ingredient;
import de.killedbycheese.recipeBookServer.recipe.util.Instruction;
import de.killedbycheese.recipeBookServer.recipe.util.PictureURL;
import de.killedbycheese.recipeBookServer.recipe.util.Recipe;
import de.killedbycheese.recipeBookServer.recipe.util.RecipeNotFoundException;

@Repository
public class RecipeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	@Autowired
	private InstructionDAO instructionDAO;
	@Autowired
	private PictureDAO pictureDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	public void setDataSource(final DataSource myRecipeDataSource) {
		jdbcTemplate = new JdbcTemplate(myRecipeDataSource);
	}
	
	public Recipe getRecipeByTitle(String title) throws RecipeNotFoundException {
		final String query = "SELECT * FROM recipe_table WHERE title = ?;";
		List<Recipe> resultList = jdbcTemplate.query(query, new Object[] { title }, new RecipeRowMapper());
		if(resultList.isEmpty() || resultList == null) throw new RecipeNotFoundException("Recipe Not Found in Database");
		Recipe result = resultList.get(0);
		
		List<Ingredient> ingredientList = ingredientDAO.getIngredientListByRecipeID(result.getId());
		for (Ingredient ingredient : ingredientList) {
			result.addIngredient(ingredient);
		}
		
		List<Instruction> instructionList = instructionDAO.getInstructionListByRecipeID(result.getId());
		for (Instruction instruction : instructionList) {
			result.addInstruction(instruction);
		}
		
		List<Category> categoryList = categoryDAO.getCategoryListByRecipeID(result.getId());
		for (Category category : categoryList) {
			result.addCategory(category);
		}
		
		List<PictureURL> pictureList = pictureDAO.getPictureListByRecipeID(result.getId());
		for (PictureURL pictureURL : pictureList) {
			result.addPictureURL(pictureURL);
		}
		
		return result;
	}

	public Vector<Recipe> getRecipeListByKeyword(String keyword, int page) throws RecipeNotFoundException {
		final String query = "select distinct recipe_table.title from category_table, ingredient_table, instruction_table, recipe_table where " + 
				"category like ? or " + 
				"ingredient like ? or " + 
				"quantity like ? or " + 
				"step like ? or " + 
				"title like ? or " + 
				"serves like ? or " + 
				"difficulty like ?; ";
		keyword = "%" + keyword + "%";
		Object[] parameters = new Object[] { keyword,keyword,keyword,keyword,keyword,keyword,keyword };
		List<String> recipeTitleList = jdbcTemplate.query(query, parameters, new RecipeTitleMapper());
		if(recipeTitleList.isEmpty() || recipeTitleList == null) throw new RecipeNotFoundException("No Recipes Found for provided Keyword");
		
		int min = (page * 10) - 10;
		int max = (page * 10) - 1;
		
		Vector<Recipe> recipeList = new Vector<>();
		for(int i = min; i < max; i++) {
			try {
				recipeList.add(getRecipeByTitle(recipeTitleList.get(i)));
			} catch(IndexOutOfBoundsException e) {
				
			}
		}
		
		return recipeList;
	}

}
