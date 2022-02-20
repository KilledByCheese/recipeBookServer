package de.killedbycheese.recipeBookServer.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	@NotEmpty(message = "Username cannot be missing")
	private String username;
	
	@NotEmpty(message = "Password cannot be missing")
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}