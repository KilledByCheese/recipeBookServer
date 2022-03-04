package de.killedbycheese.recipeBookServer.auth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.auth.entity.RecipeUser;

@Repository
public interface RecipeUserRepository extends MongoRepository<RecipeUser, String> {

	@Query("{userName: ?0}")
	RecipeUser findByUserName(String userName);
	
	@Query("{email: ?0}")
	RecipeUser findByEmail(String email);
}
