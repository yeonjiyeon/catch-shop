package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.CartResponseDto;
import springboot.catchshop.dto.OrderRequestDto;
import springboot.catchshop.service.CartService;
import springboot.catchshop.service.OrderService;
import springboot.catchshop.session.SessionConst;

import javax.validation.Valid;

/**
 * Order Controller
 * author: soohyun, last modified: 22.03.03
 */

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderServcie;

    // 주문 작성 페이지
    @GetMapping("/orders")
    public String orderForm(Model model, @ModelAttribute("orderForm") OrderRequestDto form,
                            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if (loginUser != null) { // 로그인한 사용자라면 주문 작성 페이지로
            CartResponseDto carts = cartService.orderCartList(loginUser.getId());
            model.addAttribute("carts", carts);
            return "order";
        } else { // 로그인하지 않은 사용자라면 로그인 화면으로
            return "redirect:/login";
        }
    }

    // 주문하기
    @PostMapping("/orders")
    public String order(Model model, @Valid @ModelAttribute("orderForm") OrderRequestDto form, BindingResult result,
                        @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if (result.hasErrors()) { // 주문 폼에 에러가 있는 경우
            CartResponseDto carts = cartService.orderCartList(loginUser.getId());
            model.addAttribute("carts", carts);
            return "order";
        }

        Order order = form.toEntity(loginUser);
        orderServcie.createOrder(order, loginUser.getId());
        return "redirect:/";
     }
}
