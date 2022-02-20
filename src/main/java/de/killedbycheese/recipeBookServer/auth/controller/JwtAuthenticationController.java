package de.killedbycheese.recipeBookServer.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import de.killedbycheese.recipeBookServer.auth.model.JwtRequest;
import de.killedbycheese.recipeBookServer.auth.model.JwtResponse;
import de.killedbycheese.recipeBookServer.auth.service.AuthenticationService;
import de.killedbycheese.recipeBookServer.auth.service.JwtUserDetailsService;
import de.killedbycheese.recipeBookServer.auth.util.JwtTokenUtil;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;

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

		String requestUsername = authenticationRequest.getUsername();
		String requestPassword = authenticationRequest.getPassword();

		authenticationService.authenticate(requestUsername, requestPassword);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(requestUsername);
		final String token = jwtTokenUtil.generateToken(userDetails);

		JwtResponse response = new JwtResponse(token);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/yeet")
	public ResponseEntity<?> createAuthenticationToken() throws Exception {

		return ResponseEntity.ok(userDetailsService.createUser().toString());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	    return new ErrorInfo(req.getRequestURI(), ex);
	} 

}