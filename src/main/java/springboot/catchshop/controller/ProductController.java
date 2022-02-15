package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.service.ProductService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 Q&A 기능
     * author: 강수민
     */
    @GetMapping("makeQ")
    public String makeQuestion() {
        return "make-question"; // templates/make-question.html 렌더링
    }



    /**
     * Product 기능
     * author:김지연
     */
    //상품 등록
    @PostMapping("products")
    public void createProduct(){

    }

    //상품 전체 조회
    @GetMapping("products")
    public void readProducts(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list : " + pageRequestDTO);
        model.addAttribute("result", productService.readProducts(pageRequestDTO));
    }
    

    //상품 개별 조회
    @GetMapping("products/{id}")
    public String readSingleProduct(@PathVariable Long id){
        return "single-product";
    }

    //상품 수정
    @PutMapping("products/{id}")
    public void updateProduct(){

    }
    
    //상품 삭제
    @DeleteMapping("products/{id}")
    public void deleteProduct(){

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
