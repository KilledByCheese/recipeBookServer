package de.killedbycheese.recipeBookServer.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import de.killedbycheese.recipeBookServer.auth.util.UserRole;
import de.killedbycheese.recipeBookServer.util.EnumTypePostgreSql;

@Entity
@TypeDef(name = "user_role_enum_mapper", typeClass = EnumTypePostgreSql.class)
public class RecipeUser implements Serializable {

	private static final long serialVersionUID = 7395418671693393161L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String userName;
	
	@Column(unique=true)
	private String email;
	
	private String pwHash;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "user_role_enum_mapper")
	private UserRole userRole;
	
	public RecipeUser() {
		
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
