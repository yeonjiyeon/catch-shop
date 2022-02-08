package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.domain.User;
import springboot.catchshop.session.SessionConst;

// Home Controller
// author: 강수민, created: 21.02.01
@Controller
@RequiredArgsConstructor
public class HomeController {

    /**
     * 홈화면 - 세션 처리
     * author: 강수민
     * last modified: 21.02.08
     */
    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        model.addAttribute("user", loginUser);
        return "index";
    }
}
