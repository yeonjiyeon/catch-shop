package springboot.catchshop.service;

import org.springframework.stereotype.Service;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.Review;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.ReviewDTO;

import java.util.List;

@Service
public interface ReviewService {

    //모든 상품리뷰 가져오기
    List<ReviewDTO> getListOfProduct(Long pid);

    //상품리뷰 추가
    Long register(ReviewDTO productReviewDTO);

    //상품리뷰 수정
    void modify(ReviewDTO productReviewDTO);

    //상품리뷰 삭제
    void remove(Long id);

    default Review dtoToEntity(ReviewDTO productReviewDTO){
        Review productReview = Review.builder()
                .id(productReviewDTO.getId())
                .product(Product.builder().id(productReviewDTO.getPid()).build())
                .user(User.builder().loginId(productReviewDTO.getUid()).build())
                .star(productReviewDTO.getStar())
                .contents(productReviewDTO.getContents())
                .build();

        return productReview;
    }

    default ReviewDTO entityToDTO(Review productReview){
        ReviewDTO productReviewDTO = ReviewDTO.builder()
                .id(productReview.getId())
                .pid(productReview.getProduct().getId())
                .uid(productReview.getUser().getLoginId())
                .name(productReview.getUser().getName())
                .loginId(productReview.getUser().getLoginId())
                .star(productReview.getStar())
                .contents(productReview.getContents())
                .build();

        return productReviewDTO;
    }
}
