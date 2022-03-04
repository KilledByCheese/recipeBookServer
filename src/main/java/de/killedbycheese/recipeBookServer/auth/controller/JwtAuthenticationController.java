package de.killedbycheese.recipeBookServer.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.MongoWriteException;

import org.springframework.http.HttpStatus;

import de.killedbycheese.recipeBookServer.auth.dto.JwtRequest;
import de.killedbycheese.recipeBookServer.auth.dto.RegisterRequest;
import de.killedbycheese.recipeBookServer.auth.service.AuthenticationService;
import de.killedbycheese.recipeBookServer.auth.service.JwtUserDetailsService;
import de.killedbycheese.recipeBookServer.auth.util.JwtTokenUtil;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;

//TODO Keycloak
@Controller
public class JwtAuthenticationController {
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticationService.authenticate(authenticationRequest);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerNewUser(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {

		authenticationService.registerNewUser(registerRequest);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/validate")
	public ResponseEntity<?> validateToken() throws Exception {		
		return new ResponseEntity<String>("Token is valid", HttpStatus.OK);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class, MongoWriteException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
	    return new ErrorInfo(req.getRequestURI(), ex);
	} 
	
	@ExceptionHandler({BadCredentialsException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody String
	badCredentials(HttpServletRequest req, Exception ex) {
		logger.error("Request: {} raised {}", req.getRequestURL(), ex.getLocalizedMessage());
	    return "Username/Password incorrect";
	}
	

}