package de.killedbycheese.recipeBookServer.category.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.category.service.CategoryService;
import de.killedbycheese.recipeBookServer.util.ErrorInfo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllCategories() throws Exception {
		Collection<String> allCategories = categoryService.getAllCategories();
		return new ResponseEntity<Collection<String>>(allCategories, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody String newCategory) throws Exception {
		Category createdCategory = categoryService.saveCategory(newCategory);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	    return new ErrorInfo(req.getRequestURI(), ex);
	} 
}
