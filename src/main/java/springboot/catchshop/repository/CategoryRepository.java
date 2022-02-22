package springboot.catchshop.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Category;
import springboot.catchshop.domain.Product;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Boolean existsByName(String name);


}
