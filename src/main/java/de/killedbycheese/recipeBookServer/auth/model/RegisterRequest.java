package de.killedbycheese.recipeBookServer.auth.model;

import java.io.Serializable;

import de.killedbycheese.recipeBookServer.auth.util.Role;

public class RegisterRequest implements Serializable {

	private static final long serialVersionUID = -6745708691428039562L;
	
	private String userid;
	private String password;
	private Role role;
	private String email;

	public RegisterRequest() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwhash) {
		this.password = pwhash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
