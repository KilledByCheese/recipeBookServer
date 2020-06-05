package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.killedbycheese.recipeBookServer.recipe.util.Recipe;

public class RecipeRowMapper implements RowMapper<Recipe> {

	@Override
	public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String title = rs.getString("title"); 
		long id = rs.getLong("recipeID");
		String serves = rs.getString("serves");
		String difficulty = rs.getString("difficulty");
		
		return new Recipe(title, id, serves, difficulty);
	}

	



}
