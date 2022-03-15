package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.UserRepository;
import springboot.catchshop.session.SessionConst;

import java.util.List;
import java.util.Objects;

// Member Controller
// author: 강수민, created: 22.03.01
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserRepository userRepository;

    // 전체 회원 조회
    @GetMapping("/members")
    public String getAllMembers(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                @SessionAttribute(name = SessionConst.ROLE, required = false) String role,
                                Model model) {
        if (loginUser == null) { // 로그인 하지 않은 사용자
            return "login";
        }

        if (!Objects.equals(role, "ADMIN")) { // 권한없음
            return "index";
        }

        List<User> members = userRepository.findAllByRole("USER");
        model.addAttribute("members", members);

        return "admin/members";
    }
}
