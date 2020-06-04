package de.killedbycheese.recipeBookServer.recipe.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.killedbycheese.recipeBookServer.recipe.util.Instruction;

public class InstructionRowMapper implements RowMapper<Instruction>{

	@Override
	public Instruction mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		long instructionId = rs.getLong("instructionId");
		long recipeId = rs.getLong("recipeId");
		int stepIndex = rs.getInt("stepindex");
		String step = rs.getString("step");
		
		return new Instruction(instructionId, recipeId, stepIndex, step);
	}

}
