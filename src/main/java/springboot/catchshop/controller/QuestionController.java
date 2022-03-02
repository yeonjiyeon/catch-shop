package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.QuestionCategory;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.QuestionDto;
import springboot.catchshop.service.QuestionService;
import springboot.catchshop.session.SessionConst;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/products/{id}/questions")
    public String questionForm(@ModelAttribute("questionDto") QuestionDto questionDto,
                                 @PathVariable("id") Long productId) {
        return "make-question";
    }

    @PostMapping("/products/{id}/questions")
    public String createQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @ModelAttribute QuestionDto questionDto, @PathVariable("id") Long productId) throws IOException {
        if (loginUser == null) {
            return "login";
        }
        questionService.saveQuestion(loginUser, productId, questionDto);
        return "redirect:/";
    }

    // question category select-box
    @ModelAttribute("category")
    private QuestionCategory[] questionCategories() {
        return QuestionCategory.values();
    }


}
