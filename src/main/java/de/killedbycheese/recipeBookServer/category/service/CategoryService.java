package de.killedbycheese.recipeBookServer.category.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.category.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Collection<String> getAllCategories() {
		return categoryRepository.getAllValues();
	}
	
	public Category saveCategory(String category) {
		Category newCategory = new Category();
		newCategory.setValue(category);
		Category savedCategory = categoryRepository.save(newCategory);
		return savedCategory;
	}
}
