package de.killedbycheese.recipeBookServer.auth.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.auth.dto.JwtRequest;
import de.killedbycheese.recipeBookServer.auth.dto.RegisterRequest;
import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;
import de.killedbycheese.recipeBookServer.auth.repository.RecipeUserRepository;
import de.killedbycheese.recipeBookServer.auth.util.UserRole;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RecipeUserRepository recipeUserRepository;
	
	public void authenticate(@Valid JwtRequest authenticationRequest)  {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			authenticationManager.authenticate(authentication);
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		} 
	}

	public void registerNewUser(@Valid RegisterRequest registerRequest) {

		try {
			RecipeUser newUser = new RecipeUser();
			newUser.setUserName(registerRequest.getUsername());
			newUser.setEmail(registerRequest.getEmail());
			newUser.setPwHash(BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt(12)));
			newUser.setUserRole(UserRole.USER);
			
			recipeUserRepository.insert(newUser);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

		
}
