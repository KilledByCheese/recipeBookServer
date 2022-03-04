package de.killedbycheese.recipeBookServer.category.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import de.killedbycheese.recipeBookServer.auth.repository.RecipeUserRepository;
import de.killedbycheese.recipeBookServer.auth.util.UserRole;
import de.killedbycheese.recipeBookServer.category.entity.Category;
import de.killedbycheese.recipeBookServer.category.repository.CategoryRepository;
import de.killedbycheese.recipeBookServer.util.NotAuthorizedException;

@Service
public class CategoryService {
	
	@Autowired
	private RecipeUserRepository recipeUserRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Collection<String> getAllCategories() {
		return categoryRepository.findAll().stream().map(Category::getValue).collect(Collectors.toList());
	}
	
	public Category saveCategory(String category) throws NotAuthorizedException {
		if(!(recipeUserRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getUserRole().equals(UserRole.ADMIN))) {
			throw new NotAuthorizedException("Only Admins are allowed to create Categories");
		}
		Category newCategory = new Category();
		newCategory.setValue(category);
		Category savedCategory = categoryRepository.save(newCategory);
		return savedCategory;
	}
}
