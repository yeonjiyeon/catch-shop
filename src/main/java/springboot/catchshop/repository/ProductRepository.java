package springboot.catchshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import springboot.catchshop.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>{

    @Override
    List<Product> findAll();
    Optional<Product> findById(Long id);

    @Query("select p, avg(coalesce(r.star, 0)), count(distinct r) from Product p left outer join Review r on r.product = p group by p")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select p, avg(coalesce(r.star, 0)), count(r) from Product p left outer join Review r on r.product = p where p.id = :id group by p")
    List<Object[]> getMovieWithAll(Long id);

}
