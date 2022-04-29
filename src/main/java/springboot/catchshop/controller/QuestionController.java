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
import java.util.Objects;

/**
 * 문의사항 controller
 * author: 강수민
 */
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

    // 문의 신규 등록
    @PostMapping("/products/{id}/questions")
    public String createQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @ModelAttribute QuestionDto questionDto, @PathVariable("id") Long productId) throws IOException {
        if (loginUser != null) {
            questionService.saveQuestion(loginUser, productId, questionDto);
            return "redirect:/products/" + productId;
        } else {
            return "redirect:/login";
        }
    }

    // question category select-box
    @ModelAttribute("category")
    private QuestionCategory[] questionCategories() {
        return QuestionCategory.values();
    }

    // 문의 상세 조회 - 공개글
    @GetMapping("/questions/{id}")
    public String singleQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") Long questionId, Model model) {
        if (loginUser != null) {
            Question question = questionRepository.findById(questionId).orElse(null);
            model.addAttribute("question", question);

            return "single-question";
        } else {
            return "redirect:/login";
        }

    }

    // 문의 상세 조회 - 비밀글
    @GetMapping("/questions/{id}/auth")
    public String secretQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") Long questionId, Model model, @RequestParam(value = "password", required = false, defaultValue = "") String password) {
        if (loginUser != null) {
            Question question = questionRepository.findById(questionId).orElse(null);

            // 비밀번호가 일치하는 경우
            if (Objects.equals(question.getPassword(), password)) {
                model.addAttribute("question", question);
                return "single-question";
            } else { // 비밀번호가 불일치하는 경우
                return "redirect:/";
            }
        } else {
            return "redirect:/login";
        }

    }

    // 관리자용 문의사항 전체 조회
    @GetMapping("/questions")
    public String getAllQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 Model model) {
        if (loginUser != null) {
            List<Question> questions = questionRepository.findAll();
            model.addAttribute("questions", questions);

            return "admin/questions";
        } else {
            return "redirect:/login";
        }
    }

    // 문의 수정
    @PutMapping("/questions/{id}")
    public String updateQuestion(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") Long questionId, @RequestParam("new-content-question") String content) {
        Question question = questionRepository.findById(questionId).orElseThrow( () -> new IllegalArgumentException("문의 내역이 없습니다."));
        if (loginUser != null) {
            questionService.updateQuestion(question, content);
            return "redirect:/questions/" + question.getId();
        } else {
            return "redirect:/login";
        }

    }

    // 문의 삭제
    @DeleteMapping("/questions/{id}")
    public String deleteQuesiton(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow( () -> new IllegalArgumentException("문의 내역이 없습니다."));
        if (loginUser != null) {
            questionService.deleteQuestion(question);
            return "redirect:/products/" + question.getProduct().getId();
        } else {
            return "redirect:/login";
        }
    }
}
