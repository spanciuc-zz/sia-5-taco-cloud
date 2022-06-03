package sia.tacos.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sia.tacos.domain.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
