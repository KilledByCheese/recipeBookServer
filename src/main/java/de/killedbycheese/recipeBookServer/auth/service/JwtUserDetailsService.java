package de.killedbycheese.recipeBookServer.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
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
		return new User(recipeUser.getUserName(), recipeUser.getPwHash(), new ArrayList<>());
		
	}
	
}
