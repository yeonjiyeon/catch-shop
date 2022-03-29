package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.CartResponseDto;
import springboot.catchshop.dto.OrderRequestDto;
import springboot.catchshop.dto.OrderResponseDto;
import springboot.catchshop.service.CartService;
import springboot.catchshop.service.OrderService;
import springboot.catchshop.session.SessionConst;

import javax.validation.Valid;
import java.util.List;

/**
 * Order Controller
 * author: soohyun, last modified: 22.03.19
 */

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderServcie;

    // 주문 작성 페이지
    @GetMapping("/order")
    public String orderForm(Model model, @ModelAttribute("orderForm") OrderRequestDto form,
                            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if (loginUser != null) { // 로그인한 사용자라면 주문 작성 페이지로
            CartResponseDto carts = cartService.orderCartList(loginUser.getId());
            model.addAttribute("carts", carts);
            return "orderForm";
        } else { // 로그인하지 않은 사용자라면 로그인 화면으로
            return "redirect:/login";
        }
    }

    // 주문하기
    @PostMapping("/orders")
    public String order(Model model, @Valid @ModelAttribute("orderForm") OrderRequestDto form, BindingResult result,
                        @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        CartResponseDto carts = cartService.orderCartList(loginUser.getId()); // 주문 가능한 장바구니 목록

        if (result.hasErrors()) { // 주문 폼에 에러가 있는 경우
            model.addAttribute("carts", carts);
            return "orderForm";
        }

        Order order = form.toEntity(loginUser, carts.getTotalAllProductPrice(), carts.getShippingFee());
        orderServcie.createOrder(order, loginUser.getId(), carts.getCartList());
        return "redirect:/orders";
    }

    // 주문 내역 조회
    @GetMapping("/orders")
    public String orderList(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (loginUser != null) { // 로그인한 사용자라면 주문 내역 화면으로
            List<OrderResponseDto> orders = orderServcie.orderList(loginUser);
            model.addAttribute("orders", orders);
            return "order";
        } else { // 로그인하지 않은 사용자라면 로그인 화면으로
            return "redirect:/login";
        }
    }

    // 주문 취소
    @DeleteMapping("/orders/{id}")
    public String cancelOrder(@PathVariable("id") Long orderId) {
        orderServcie.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
