package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.Question;
import springboot.catchshop.domain.QuestionCategory;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.QuestionDto;
import springboot.catchshop.repository.QuestionRepository;
import springboot.catchshop.service.QuestionService;
import springboot.catchshop.session.SessionConst;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

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

    @GetMapping("/questions/{id}")
    public String singleQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") Long questionId, Model model) {
        if (loginUser == null) {
            return "login";
        }
        Optional<Question> question = questionRepository.findById(questionId);
        model.addAttribute("question", question);

        return "single-question";
    }

    @GetMapping("/questions")
    public String getAllQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 Model model) {
        if (loginUser == null) {
            return "login";
        }

        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);

        return "admin/questions";
    }
}
