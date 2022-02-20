package de.killedbycheese.recipeBookServer.category.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.killedbycheese.recipeBookServer.category.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
