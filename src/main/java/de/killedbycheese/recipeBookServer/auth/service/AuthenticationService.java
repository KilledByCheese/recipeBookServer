package de.killedbycheese.recipeBookServer.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public void authenticate(String username, String password)  {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(authentication);
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		} 
	}
}
