package springboot.catchshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {





}
