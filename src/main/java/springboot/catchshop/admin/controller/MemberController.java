package springboot.catchshop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.admin.service.MemberService;
import springboot.catchshop.domain.Question;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.OrderResponseDto;
import springboot.catchshop.dto.QuestionDto;
import springboot.catchshop.repository.QuestionRepository;
import springboot.catchshop.repository.UserRepository;
import springboot.catchshop.service.OrderService;
import springboot.catchshop.session.SessionConst;

import java.util.List;

// Member Controller
// author: 강수민, created: 22.03.01
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final QuestionRepository questionRepository;

    @GetMapping("/members")
    public String getAllMembers(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                    Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        if (loginUser == null) {
            return "login";
        }
        Page<User> memberList = memberService.getAllMembersWithPaging(page);
        model.addAttribute("paging", memberList);

        Page<User> kakaoMemberList = memberService.getAllKakaoMembersWithPaging(page);
        model.addAttribute("pagingKakao", kakaoMemberList);

        return "admin/members";
    }

    // 특정 회원 주문 내역 모두 조회
    @GetMapping("members/{id}/orders")
    public String getAllOrdersByMember(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                       @PathVariable("id") Long id, Model model) {
        if (loginUser != null) {
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
            List<OrderResponseDto> orders = orderService.orderList(user);
            model.addAttribute("orders", orders);
            return "order";
        } else {
            return "redirect:/login";
        }
    }

    // 특정 회원 문의 내역 모두 조회
    @GetMapping("members/{id}/questions")
    public String getAllQuestionsByMember(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                       @PathVariable("id") Long id, Model model) {
        if (loginUser != null) {
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
            List<Question> questions = questionRepository.findByUser(user);
            model.addAttribute("questions", questions);
            return "admin/questions";
        } else {
            return "redirect:/login";
        }
    }
}
