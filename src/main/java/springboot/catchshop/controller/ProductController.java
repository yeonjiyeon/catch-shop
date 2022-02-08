package springboot.catchshop.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ProductController {

    // qna 질문 작성
    @GetMapping("makeQ")
    public String makeQuestion() {
        return "make-question"; // templates/make-question.html 렌더링
    }



    /**
     * Product 기능
     * author:김지연
     */
    //
    //상품 목록 조회
    @GetMapping("products")
    public String readProducts() {
        log.info("================list============");
        return "shoplist";
    }




    /**
     * review 기능
     * author:김지연
     */
    //review 목록 조회
    @GetMapping("reviews")
    public String readReview() {
        log.info("================review============");
        return "review";
    }
}
