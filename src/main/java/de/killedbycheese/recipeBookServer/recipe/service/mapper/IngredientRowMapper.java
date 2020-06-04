package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.killedbycheese.recipeBookServer.recipe.util.Ingredient;

public class IngredientRowMapper implements RowMapper<Ingredient> {

	@Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		long ingredientId = rs.getLong("ingredientId");
		long recipeId = rs.getLong("recipeId");
		String ingredient = rs.getString("ingredient");
		String quantity = rs.getString("quantity");
		
		return new Ingredient(ingredientId, recipeId, ingredient, quantity);
	}

}
