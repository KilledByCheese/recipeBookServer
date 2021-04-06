package de.killedbycheese.recipeBookServer.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import de.killedbycheese.recipeBookServer.category.model.CategoryDTO;
import de.killedbycheese.recipeBookServer.category.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken() throws Exception {
		
		List<CategoryDTO> categoryList = categoryService.getAllCategoriesAsList();
		if(categoryList != null) {
			return new ResponseEntity<List<CategoryDTO>>(categoryList, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong fetching Categories", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
