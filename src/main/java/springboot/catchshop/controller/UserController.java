package springboot.catchshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // 로그인
    @GetMapping("login")
    public String login() {
        return "login"; // templates/login.html 렌더링
    }

    // 회원가입
    @GetMapping("join")
    public String join() {
        return "join"; // templates/join.html 렌더링
    }

    // 아이디, 비밀번호 찾기
    @GetMapping("findidpw")
    public String findidpw() {
        return "find-id-pw"; // templates/find-id-pw.html 렌더링
    }
}
