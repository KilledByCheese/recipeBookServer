package de.killedbycheese.recipeBookServer.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class RegisterRequest implements Serializable {

	private static final long serialVersionUID = -6745708691428039562L;

	@NotEmpty(message = "Username cannot be missing")
	private String username;

	@NotEmpty(message = "Email cannot be missing")
	private String email;

	@NotEmpty(message = "Password cannot be missing")
	private String password;

	public RegisterRequest() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
