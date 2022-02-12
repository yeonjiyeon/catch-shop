package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.User;
import springboot.catchshop.service.CartService;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

// Cart Controller
// author: soohyun, last modified: 22.02.12

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("/carts/{id}")
    public String addCart(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                          @PathVariable("id") Long productId,
                          @RequestParam("count") int count) {
        log.println(loginUser);
        if (loginUser != null) {
            Long cartId = cartService.addCart(loginUser.getId(), productId, count);
        }

        return "redirect:/carts";
    }

    // 장바구니 조회
    @GetMapping("/carts")
    public String cartList(HttpSession session) {
        return "cart";
    }

    // 장바구니 변경
    @PutMapping("/carts/{id}")
    public void updateCart() {

    }

    // 장바구니 삭제
    @DeleteMapping("/carts/{id}")
    public void deleteCart() {

    }
}
