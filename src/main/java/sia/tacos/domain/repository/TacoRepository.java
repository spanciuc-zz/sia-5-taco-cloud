package sia.tacos.domain.repository;

import sia.tacos.domain.model.Taco;

public interface TacoRepository {

	Taco save(Taco taco);
	
}
