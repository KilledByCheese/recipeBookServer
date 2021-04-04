package de.killedbycheese.recipeBookServer.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.killedbycheese.recipeBookServer.auth.exception.EmailAlreadyInUseXception;
import de.killedbycheese.recipeBookServer.auth.exception.UserIdAlreadyInUseXception;
import de.killedbycheese.recipeBookServer.auth.model.JwtRequest;
import de.killedbycheese.recipeBookServer.auth.model.JwtResponse;
import de.killedbycheese.recipeBookServer.auth.model.RegisterRequest;
import de.killedbycheese.recipeBookServer.auth.service.JwtUserDetailsService;
import de.killedbycheese.recipeBookServer.auth.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		//Authenticate User by Username and password
		String requestUsername = authenticationRequest.getUsername();
		String requestPassword = authenticationRequest.getPassword();
		authenticate(requestUsername, requestPassword);
		//Get User Details by Username
		final UserDetails userDetails = userDetailsService.loadUserByUsername(requestUsername);
		//Generate Token based on User Details
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		//Return Response
		JwtResponse response = new JwtResponse(token);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerNewUser(@RequestBody RegisterRequest registerRequest) throws Exception {
		
		boolean created;
		try {
			created = userDetailsService.register(registerRequest);
			if(created == true) {
				return new ResponseEntity<String>("User successfully created", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Failed To create user - unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (EmailAlreadyInUseXception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (UserIdAlreadyInUseXception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	

	private void authenticate(String username, String password)  {
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