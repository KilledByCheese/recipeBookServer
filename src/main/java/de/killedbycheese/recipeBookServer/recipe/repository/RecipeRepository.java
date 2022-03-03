package de.killedbycheese.recipeBookServer.recipe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.killedbycheese.recipeBookServer.recipe.entity.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, Long> {

}
