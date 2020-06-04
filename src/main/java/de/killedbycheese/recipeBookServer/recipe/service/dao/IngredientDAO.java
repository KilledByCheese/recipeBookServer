package de.killedbycheese.recipeBookServer.recipe.service.dao;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.recipe.service.mapper.IngredientRowMapper;
import de.killedbycheese.recipeBookServer.recipe.util.Ingredient;

@Repository
public class IngredientDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(final DataSource myRecipeDataSource) {
		jdbcTemplate = new JdbcTemplate(myRecipeDataSource);
	}
	
	public List<Ingredient> getIngredientListByRecipeID(long recipeID) {
		final String query = "SELECT * FROM ingredient_table WHERE recipeID = ?";
		return jdbcTemplate.query(query, new Object[] { recipeID }, new IngredientRowMapper());
	}

}
