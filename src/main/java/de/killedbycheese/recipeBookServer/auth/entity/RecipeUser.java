package de.killedbycheese.recipeBookServer.auth.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import de.killedbycheese.recipeBookServer.auth.util.UserRole;
import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;

@Document
public class RecipeUser {

	@Id
	private String recipeUserId;
	
	@Indexed(unique = true)
	private String userName;
	
	@Indexed(unique = true)
	private String email;
	
	private String pwHash;
	
	private UserRole userRole;
	
	@DocumentReference
	private List<Recipe> recipes = new ArrayList<Recipe>();
	
	public RecipeUser() {
		
	}

	public String getRecipeUserId() {
		return recipeUserId;
	}

	public void setRecipeUserId(String recipeUserId) {
		this.recipeUserId = recipeUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwHash() {
		return pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	
		
}
	
	
	
