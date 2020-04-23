package rubusdata.location.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubusdata.location.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}