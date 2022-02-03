package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
