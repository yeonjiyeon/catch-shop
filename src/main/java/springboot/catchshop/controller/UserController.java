package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.catchshop.form.JoinForm;
import springboot.catchshop.form.LoginForm;
import springboot.catchshop.domain.Address;
import springboot.catchshop.domain.Role;
import springboot.catchshop.domain.User;
import springboot.catchshop.service.UserService;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * author: 강수민
     */
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "join"; // templates/join.html 렌더링
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "join";
        }

        User user = new User();
        user.setLoginId(form.getLoginId());

        // 비밀번호 인코딩
//        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(form.getPassword());

        user.setName(form.getName());
        user.setTelephone(form.getTelephone());
        Address address = new Address(form.getRoad(), form.getDetail(), form.getPostalcode());
        user.setAddress(address);
        user.setRole(Role.USER);
        user.setJoindate(LocalDateTime.now());

        userService.join(user);
        return "redirect:/";
    }

    /**
     * 로그인
     * author: 강수민
     */
    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "login"; // templates/login.html 렌더링
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User loginUser = userService.login(form.getLoginId(), form.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

        // 세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }

    /**
     * 로그아웃
     * author: 강수민
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
//        expireCookie(request, response, "user_id");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    // 아이디, 비밀번호 찾기
    @GetMapping("/findidpw")
    public String findidpw() {
        return "find-id-pw"; // templates/find-id-pw.html 렌더링
    }
}
