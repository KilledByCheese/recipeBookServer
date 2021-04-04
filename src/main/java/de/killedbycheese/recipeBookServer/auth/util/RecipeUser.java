package de.killedbycheese.recipeBookServer.auth.util;

import java.io.Serializable;

public class RecipeUser implements Serializable {

	private static final long serialVersionUID = -2929798991180064492L;

	private String userid;
	private Role role;
	private String pwhash;
	private String email;
	
	public RecipeUser() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPwhash() {
		return pwhash;
	}

	public void setPwhash(String pwhash) {
		this.pwhash = pwhash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RecipeUser [userid=" + userid + ", role=" + role + ", pwhash=" + pwhash + ", email=" + email + "]";
	}
	
	
	
	
}
