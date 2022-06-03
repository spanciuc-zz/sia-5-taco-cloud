package sia.tacos.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.tacos.domain.model.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Integer> {

}
