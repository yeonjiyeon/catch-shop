package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Category;
import springboot.catchshop.domain.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {




}
