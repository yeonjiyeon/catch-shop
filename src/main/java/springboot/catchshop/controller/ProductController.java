package springboot.catchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

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
    @GetMapping("index_2")
    public String indexTwo() {
        return "index"; // templates/shoplist.html 렌더링
    }

    @GetMapping("products")
    public String readProducts() {
        return "shoplist"; // templates/shoplist.html 렌더링
    }


    /**
     * review 기능
     * author:김지연
     */
}
