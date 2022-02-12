package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.UserRepository;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    /**
     * 홈화면 - 세션 처리
     * author: 강수민
     */
    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {

        // 세션에 회원 데이터가 없는 경우
        if (loginUser == null) {
            return "index";
        }

        // 세션이 유지되는 경우
        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}
