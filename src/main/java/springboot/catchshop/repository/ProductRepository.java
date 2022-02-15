package springboot.catchshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.catchshop.domain.Product;

import javax.persistence.EntityManager;

public interface ProductRepository extends JpaRepository<Product, Long> {




    //paging처리
    //Page<Product> findByProductBetween(Long from, Long to, Pageable pageable);


}
