package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
