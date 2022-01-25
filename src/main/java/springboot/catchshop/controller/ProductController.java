package springboot.catchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    // qna 질문 작성
    @GetMapping("makeQ")
    public String makeQuestion() {
        return "make-question"; // templates/make-question.html 렌더링
    }
}
