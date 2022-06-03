package sia.tacos.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.tacos.domain.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
}
