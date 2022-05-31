package sia.tacos.domain.repository;

import sia.tacos.domain.model.Order;

public interface OrderRepository {

	Order save(Order order);

}
