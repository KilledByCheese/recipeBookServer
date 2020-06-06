package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RecipeIdMapper implements RowMapper<Long>{

	@Override
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		long recipeID = rs.getLong("recipeID");
		
		return recipeID;
	}

}
