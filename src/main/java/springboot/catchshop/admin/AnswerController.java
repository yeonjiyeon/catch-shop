package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.Answer;
import springboot.catchshop.domain.User;
import springboot.catchshop.session.SessionConst;

// Answer Controller
// author: 강수민, created: 22.03.15
// last modified: 22.03.15
@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    @GetMapping("/questions/{id}/answers")
    public String getQnA(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                         @ModelAttribute("answerDto") AnswerDto form, @PathVariable Long id) {
        return "admin/answer";
    }

    @PostMapping("/questions/{id}/answers")
    public String createAnswer(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                               @ModelAttribute AnswerDto form, @PathVariable Long id) {
        answerService.save(loginUser, id, form);
        return "redirect:/";
    }

    @GetMapping("/answers/{id}/edit")
    public String getAnswer(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                         @ModelAttribute("answerDto") AnswerDto form, @PathVariable("id") Long answerId,
                            Model model) {
        Answer answer = answerRepository.findById(answerId).orElse(null);
        model.addAttribute("answer", answer);
        return "admin/update-answer";
    }

    @PutMapping("/answers/{id}/edit")
    public String updateAnswer(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                               @ModelAttribute AnswerDto form, @PathVariable("id") Long answerId) {
        answerService.updateAnswer(answerId, form);
        return "redirect:/";
    }

    // TODO 삭제
}
