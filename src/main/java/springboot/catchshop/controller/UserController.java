package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.catchshop.dto.FindIdDto;
import springboot.catchshop.dto.JoinDto;
import springboot.catchshop.dto.LoginDto;
import springboot.catchshop.domain.Role;
import springboot.catchshop.domain.User;
import springboot.catchshop.service.UserService;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

// User Controller
// author: 강수민, created: 22.02.01
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * author: 강수민
     * last modified: 22.02.01
     */
    @GetMapping("/join")
    public String join(@ModelAttribute("joinDto") JoinDto form) {
//        model.addAttribute("joinForm", new JoinDto());
        return "join"; // templates/join.html 렌더링
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "join";
        }

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        JoinDto joinDto = new JoinDto(form.getLoginId(), encodedPassword,
                form.getName(), form.getTelephone(),
                form.getRoad(), form.getDetail(), form.getPostalcode(),
                Role.USER, LocalDateTime.now());

        User user = joinDto.toEntity();
        userService.join(user);
        return "redirect:/";
    }

    /**
     * 로그인
     * author: 강수민
     * last modified: 22.02.08
     */
    @GetMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto form) {
        return "login"; // templates/login.html 렌더링
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        LoginDto loginDto = new LoginDto(form.getLoginId(), form.getPassword());
        User loginUser = userService.login(loginDto.getLoginId(), loginDto.getPassword());

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
     * last modified: 22.02.08
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
    @GetMapping("/find-id-pw")
    public String findIdPw(@ModelAttribute("findIdDto") FindIdDto form) {
        return "find-id-pw"; // templates/find-id-pw.html 렌더링
    }

    /**
     * 아이디 찾기
     * author: 강수민
     * last modified: 22.02.08
     */
    @PostMapping("/find-id-pw")
    public String findId(@Valid @ModelAttribute FindIdDto form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "find-id-pw";
        }

        User user = userService.findId(form.getName(), form.getTelephone());

        if (user == null) {
            bindingResult.reject("findIdFail", "일치하는 사용자가 없습니다.");
            return "find-id-pw";
        }

        model.addAttribute("loginId", user.getLoginId());
        return "find-id-pw";
    }
}
