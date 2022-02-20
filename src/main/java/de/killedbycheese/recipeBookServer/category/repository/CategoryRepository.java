package de.killedbycheese.recipeBookServer.category.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.category.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	@Query(value = "SELECT value FROM category", nativeQuery = true)
	Collection<String> getAllValues();

}
