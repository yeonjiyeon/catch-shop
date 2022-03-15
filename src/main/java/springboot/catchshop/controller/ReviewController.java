package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.dto.ReviewDTO;
import springboot.catchshop.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //모든 상품리뷰 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("id") Long id){
        List<ReviewDTO> reviewDTOList = reviewService.getListOfProduct(id);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    //상품리뷰 추가
    @PostMapping("/{id}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO productReviewDTO){
        Long id = reviewService.register(productReviewDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //상품리뷰 수정
    @PutMapping("/{uid}/{id}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long id
            , @RequestBody ReviewDTO productReviewDTO){
        reviewService.modify(productReviewDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //상품리뷰 삭제
    @DeleteMapping("/{uid}/{id}")
    public ResponseEntity<Long> removeReview(@PathVariable Long id){
        reviewService.remove(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
