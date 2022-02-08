package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
