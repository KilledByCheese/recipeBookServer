package de.killedbycheese.recipeBookServer.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.auth.model.RecipeUser;
import de.killedbycheese.recipeBookServer.auth.repository.RecipeUserRepository;
import de.killedbycheese.recipeBookServer.auth.util.UserRole;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	RecipeUserRepository recipeUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RecipeUser recipeUser = recipeUserRepository.findByUserName(username);
		 
		if (recipeUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(recipeUser.getId().toString(), recipeUser.getPwHash(), new ArrayList<>());
		
	}

	public RecipeUser createUser() {
		// TODO Auto-generated method stub
		RecipeUser temp = new RecipeUser();
		temp.setEmail("test@example.com");
		temp.setUserName("testUser");
		temp.setPwHash("$2a$12$d.YOJq1tut8TKGd/hyKNyukZ0rbOJ1L59fkJGWJNrqdSdEqC.OmYO");
		temp.setUserRole(UserRole.USER);
		return recipeUserRepository.save(temp);
	}
}
