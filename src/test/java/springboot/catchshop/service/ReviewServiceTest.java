package springboot.catchshop.service;

import com.mysema.commons.lang.Assert;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;
import springboot.catchshop.domain.Review;
import springboot.catchshop.repository.ReviewRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReviewServiceTest {
    @Autowired
    ReviewRepository reviewRepository;

    @DisplayName("날짜 테스트")
    @Test
    public void BaseTimeEntityTest(){
        //given
        LocalDateTime now = LocalDateTime.now();
        reviewRepository.save(new Review());

        //when
        List<Review> reviews = reviewRepository.findAll();

        //then
        Review review = reviews.get(0);
        System.out.println(">>>>>>>>registerDate = " + review.getRegDate());

        //assertEquals();
        assertNotEquals(review.getRegDate(), now);
    }
}
