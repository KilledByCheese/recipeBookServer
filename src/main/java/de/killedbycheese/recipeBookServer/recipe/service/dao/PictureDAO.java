package de.killedbycheese.recipeBookServer.recipe.service.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.recipe.service.mapper.PictureRowMapper;
import de.killedbycheese.recipeBookServer.recipe.util.PictureURL;

@Repository
public class PictureDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(final DataSource myRecipeDataSource) {
		jdbcTemplate = new JdbcTemplate(myRecipeDataSource);
	}
	
	public List<PictureURL> getPictureListByRecipeID(long recipeID) {
		final String query = "SELECT * FROM picture_table WHERE recipeID = ?;";
		return jdbcTemplate.query(query, new Object[] { recipeID }, new PictureRowMapper());
	}

}
