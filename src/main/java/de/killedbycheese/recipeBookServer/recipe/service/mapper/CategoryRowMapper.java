package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.killedbycheese.recipeBookServer.recipe.util.Category;

public class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		long recipeID = rs.getLong("recipeID");
		long categoryID = rs.getLong("categoryID");
		String category = rs.getString("category");
		
		return new Category(recipeID, categoryID, category);
	}


}
