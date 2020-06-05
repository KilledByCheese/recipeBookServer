package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RecipeTitleMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String recipeTitle = rs.getString("title");
		
		return recipeTitle;
	}


}
