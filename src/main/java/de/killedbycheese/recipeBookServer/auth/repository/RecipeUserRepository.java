package de.killedbycheese.recipeBookServer.auth.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.auth.model.RecipeUser;

@Repository
public interface RecipeUserRepository extends CrudRepository<RecipeUser, Long> {

	RecipeUser findByUserName(String userName);
	RecipeUser findByEmail(String email);
}
