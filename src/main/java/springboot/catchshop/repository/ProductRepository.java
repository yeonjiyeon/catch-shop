package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.catchshop.domain.Product;

import javax.persistence.EntityManager;

public interface ProductRepository extends JpaRepository<Product, Long> {


    //insert

    //selectAll

    //selectOne

    //update

    //delete


}
