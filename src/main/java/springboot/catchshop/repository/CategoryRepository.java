package springboot.catchshop.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Category;
import springboot.catchshop.domain.Product;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
    Optional<Category> findByBranchAndName(String branch, String name);

    Boolean existsByBranchAndName(String branch, String name);


}
