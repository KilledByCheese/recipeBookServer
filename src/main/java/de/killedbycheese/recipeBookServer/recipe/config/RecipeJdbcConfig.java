package de.killedbycheese.recipeBookServer.recipe.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class RecipeJdbcConfig {
	
	@Value("${recipe.db.url}")
	private String recipeDBurl;
	
	@Value("${recipe.db.user}")
	private String recipeDBuser;
	
	@Value("${recipe.db.pw}")
	private String recipeDBpw;

	@Bean
	public DataSource myRecipeDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setUrl(recipeDBurl);
		dataSource.setUsername(recipeDBuser);
		dataSource.setPassword(recipeDBpw);
		
		return dataSource;
	}
	
	
}
