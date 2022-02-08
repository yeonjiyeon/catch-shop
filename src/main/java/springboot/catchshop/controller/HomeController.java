package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.UserRepository;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String homeLogin(@CookieValue(name="user_id", required = false) Long user_id, Model model) {
        if (user_id == null) {
            return "index";
        }

        // 로그인
        Optional<User> loginUser = userRepository.findById(user_id);
        if (!loginUser.isPresent()) {
            return "index";
        }

        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}
