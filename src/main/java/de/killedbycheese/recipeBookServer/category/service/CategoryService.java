package de.killedbycheese.recipeBookServer.category.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.category.model.CategoryDTO;

@Repository
public class CategoryService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM category_table";

	public List<CategoryDTO> getAllCategoriesAsList() {
		return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, categoryRowMapper);
	}

	
	private RowMapper<CategoryDTO> categoryRowMapper = ((rs, rowNum) -> {
		CategoryDTO category = new CategoryDTO();
		category.setCategoryid(rs.getInt("categoryid"));
		category.setCategory(rs.getString("category"));
		return category;
	});
}
