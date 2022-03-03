package de.killedbycheese.recipeBookServer.category.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.category.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long>{
		
	@Query("{value: ?0}")
	Category findByValue(String value);

}
