package de.killedbycheese.recipeBookServer.category.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.killedbycheese.recipeBookServer.auth.dto.JwtRequest;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@GetMapping(value = "/all")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerNewUser() throws Exception {
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
		
	}

	@ExceptionHandler({})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	    return new ErrorInfo(req.getRequestURI(), ex);
	} 
}
