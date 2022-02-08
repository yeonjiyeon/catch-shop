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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
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

    // 로그인
    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "login"; // templates/login.html 렌더링
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User loginUser = userService.login(form.getLoginId(), form.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

        // 로그인 성공 처리

        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키 (브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("user_id", String.valueOf(loginUser.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        expireCookie(request, response, "user_id");
        return "redirect:/";
    }

    private void expireCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
    }

    // 아이디, 비밀번호 찾기
    @GetMapping("/findidpw")
    public String findidpw() {
        return "find-id-pw"; // templates/find-id-pw.html 렌더링
    }
}
