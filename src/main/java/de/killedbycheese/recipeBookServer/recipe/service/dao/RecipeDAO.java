package de.killedbycheese.recipeBookServer.recipe.service.dao;

import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.recipe.service.mapper.RecipeTitleMapper;
import de.killedbycheese.recipeBookServer.recipe.service.mapper.RecipeIdMapper;
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
		if(resultList.isEmpty() || resultList == null) throw new RecipeNotFoundException("No Recipe found for Title: " + title);
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
		keyword = "%" + keyword + "%";
		
		final String queryCategory = "select recipeid from category_table where category like ? ;";
		final String queryIngredient = "select recipeid from ingredient_table where ingredient like ? or quantity like ? ;";
		final String queryStep = "select recipeid from instruction_table where step like ? ;";
		final String queryRecipe = "select recipeid from recipe_table where title like ? or serves like ? or difficulty like ? ;";
		
		List<Long> idListCategory = jdbcTemplate.query(queryCategory, new Object[] { keyword }, new RecipeIdMapper());
		List<Long> idListIngredient = jdbcTemplate.query(queryIngredient, new Object[] { keyword, keyword }, new RecipeIdMapper());
		List<Long> idListStep = jdbcTemplate.query(queryStep, new Object[] { keyword }, new RecipeIdMapper());
		List<Long> idListRecipe = jdbcTemplate.query(queryRecipe, new Object[] { keyword, keyword, keyword }, new RecipeIdMapper());
		
		Vector<Recipe> recipeList = new Vector<>();
		recipeList.addAll(getRecipeListByIdList(idListCategory));
		recipeList.addAll(getRecipeListByIdList(idListIngredient));
		recipeList.addAll(getRecipeListByIdList(idListStep));
		recipeList.addAll(getRecipeListByIdList(idListRecipe));
		
		if(recipeList.isEmpty() || recipeList == null) throw new RecipeNotFoundException("No Recipes Found for provided Keyword: " + keyword);
				
		return paginateList(recipeList, page);
	}

	public Vector<Recipe> getRecipeListByCategoryList(List<String> categoryList, int page) throws RecipeNotFoundException {
		
		Vector<Recipe> recipeListByCategoryList = new Vector<>();
		
		for (String category : categoryList) {
			
			Vector<Recipe> copy = new Vector<>();
			
			Vector<Recipe> recipeListByCategory = getRecipeListByCategory(category);
//			for (Recipe recipe : recipeListByCategory) {
//				System.out.println("==> for " + category + " found " + recipe.getTitle() );
//			}
			
			if(recipeListByCategoryList.isEmpty()) {
				recipeListByCategoryList.addAll(recipeListByCategory);
//				System.out.println("==> added all found recipes because empty");
			} else {
				for (Recipe recipe : recipeListByCategory) {
					
					if(recipeListByCategoryList.contains(recipe)) {
						copy.add(recipe);
//						System.out.println("==> Rezept " + recipe.getTitle() + " bereits vorhanden adding to copy");
					} else {
//						System.out.println("Rezept "+recipe.getTitle()+" nicht vorhanden not adding to copy");
					}
				}
				recipeListByCategoryList = copy;
//				System.out.println("==> List = copy");
			}
			
		}
				
		
		return paginateList(recipeListByCategoryList, page);
	}
	
	private Vector<Recipe> getRecipeListByCategory(String category) throws RecipeNotFoundException {
		String categoryQuery = "%"+category+"%";
		final String query = "select distinct recipeid from category_table where category like ? ;";
		List<Long> recipeIdList = jdbcTemplate.query(query, new Object[] { categoryQuery }, new RecipeIdMapper());
		if(recipeIdList.isEmpty() || recipeIdList == null) throw new RecipeNotFoundException("No recipe Found for category: " + category);
		
		Vector<Recipe> recipeList = getRecipeListByIdList(recipeIdList);
		
		return recipeList;
	}

	private Vector<Recipe> getRecipeListByIdList(List<Long> recipeIdList) throws RecipeNotFoundException {
		final String query = "select title from recipe_table where recipeid = ? ;";
		
		Vector<String> titleList = new Vector<>();
		
		for (Long id : recipeIdList) {
			List<String> title = jdbcTemplate.query(query, new Object[] {id}, new RecipeTitleMapper());
			titleList.add(title.get(0));
		}
		
		Vector<Recipe> recipeList = new Vector<>();
		for (String title : titleList) {
			recipeList.add(getRecipeByTitle(title));
		}
		
		return recipeList;
	}

	public Vector<Recipe> getRecipeList(int page) throws RecipeNotFoundException {
		final String query = "select distinct recipeid from recipe_table ;";
		List<Long> recipeIdList = jdbcTemplate.query(query, new RecipeIdMapper());
		
		Vector<Recipe> recipeList = getRecipeListByIdList(recipeIdList);
		
		return paginateList(recipeList, page);
	}
	
	private Vector<Recipe> paginateList(List<Recipe> recipeList, int page) {
		int min = (page * 10) - 10;
		int max = (page * 10) - 1;
		
		Vector<Recipe> returnList = new Vector<>();
		for(int i = min; i < max; i++) {
			try {
				returnList.add(recipeList.get(i));
			} catch(IndexOutOfBoundsException e) {
				//DO nothing
			}
		}
		
		return returnList;
	}

}
