package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.dto.*;
import springboot.catchshop.domain.Role;
import springboot.catchshop.domain.User;
import springboot.catchshop.service.MailService;
import springboot.catchshop.service.UserService;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

// User Controller
// author: 강수민, created: 22.02.01
@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    /**
     * 회원가입
     * author: 강수민
     * last modified: 22.02.01
     */

    @GetMapping("/join")
    public String join(@ModelAttribute("joinDto") JoinDto form) {
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
                form.getRole(), LocalDateTime.now());

        User user = joinDto.toEntity();
        userService.join(user);
        return "redirect:/";
    }

    // 권한 설정 라디오 박스 값 전달
    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
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
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * 아이디 찾기
     * author: 강수민
     * last modified: 22.02.08
     */
    @GetMapping("/find-id")
    public String findId(@ModelAttribute("findIdDto") FindIdDto form) {
        return "find-id"; // templates/find-id.html 렌더링
    }

    @PostMapping("/find-id")
    public String findId(@Valid @ModelAttribute FindIdDto form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "find-id";
        }

        User user = userService.findId(form.getName(), form.getTelephone());

        if (user == null) {
            bindingResult.reject("findIdFail", "일치하는 사용자가 없습니다.");
            return "find-id";
        }

        model.addAttribute("userId", user.getLoginId());
        return "find-id";
    }

    /**
     * 비밀번호 찾기
     * author: 강수민
     * last modified: 22.02.08
     */
    @GetMapping("/find-pw")
    public String findPw(@ModelAttribute("findPwDto") FindPwDto form) {
        return "find-pw"; // templates/find-pw.html 렌더링
    }

    @PostMapping("/find-pw")
    public String findPw(@Valid @ModelAttribute FindPwDto form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "find-pw";
        }

        User user = userService.findPw(form.getLoginId());

        if (user == null) {
            bindingResult.reject("findPwFail", "일치하는 사용자가 없습니다.");
            return "find-pw";
        }

        // 임시 비밀번호 생성 및 저장
        String newPassword = userService.updatePw(user);

        // 임시 비밀번호 발급 메일 전송
        MailDto mailDto = new MailDto(form.getEmail(),
                "캐치샵 임시 비밀번호 발급 메일입니다.",
                "임시 비밀번호는 " + newPassword + " 입니다.");
        String success = mailService.sendMail(mailDto);

        model.addAttribute("success", success);
        return "find-pw";
    }

    /**
     * 마이페이지
     * author: 강수민
     * last modified: 22.02.15
     */
    @GetMapping("/mypage")
    public String mypage(@ModelAttribute("updateUserDto") UpdateUserDto form,
                         @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                         Model model) {
        User user = userService.findById(loginUser.getId());
        model.addAttribute("user", user);
        return "mypage"; // templates/mypage.html 렌더링
    }

    @PostMapping("/mypage")
    public String mypage(@Valid @ModelAttribute UpdateUserDto form, BindingResult result) {

        if (result.hasErrors()) {
            return "mypage";
        }

        return "redirect:/";
    }
}
