package springboot.catchshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    //insert

    //selectAll

    //selectOne

    //update

    //delete

    //paging처리
    //Page<Review> findByReviewBetween(Long from, Long to, Pageable pageable);


}
