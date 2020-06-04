package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.killedbycheese.recipeBookServer.recipe.util.PictureURL;

public class PictureRowMapper implements RowMapper<PictureURL>{

	@Override
	public PictureURL mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		long pictureID = rs.getLong("pictureID");
		long recipeID = rs.getLong("recipeID");
		String pictureURL = rs.getString("pictureLink");
		
		return new PictureURL(pictureID, recipeID, pictureURL);
	}

}
