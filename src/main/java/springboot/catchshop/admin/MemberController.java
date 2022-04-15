package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.UserRepository;
import springboot.catchshop.session.SessionConst;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Objects;

// Member Controller
// author: 강수민, created: 22.03.01
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String getAllMembers(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                    Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        if (loginUser == null) {
            return "login";
        }
        Page<User> memberList = memberService.getAllMembersWithPaging(page);
        model.addAttribute("paging", memberList);

        return "admin/members";
    }
}
