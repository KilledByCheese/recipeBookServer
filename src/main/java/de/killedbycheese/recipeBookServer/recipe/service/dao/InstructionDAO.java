package de.killedbycheese.recipeBookServer.recipe.service.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.recipe.service.mapper.InstructionRowMapper;
import de.killedbycheese.recipeBookServer.recipe.util.Instruction;

@Repository
public class InstructionDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(final DataSource myRecipeDataSource) {
		jdbcTemplate = new JdbcTemplate(myRecipeDataSource);
	}
	
	public List<Instruction> getInstructionListByRecipeID(long recipeID) {
		final String query = "SELECT * FROM instruction_table WHERE recipeID = ?";
		return jdbcTemplate.query(query, new Object[] { recipeID }, new InstructionRowMapper());
	}

}
