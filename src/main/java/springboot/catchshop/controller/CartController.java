package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.CartDto;
import springboot.catchshop.dto.CartListDto;
import springboot.catchshop.dto.CartResponseDto;
import springboot.catchshop.service.CartService;
import springboot.catchshop.session.SessionConst;

import javax.servlet.http.HttpSession;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

// Cart Controller
// author: soohyun, last modified: 22.02.14

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("/carts/{id}")
    public String addCart(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                          @PathVariable("id") Long productId,
                          @RequestParam("count") int count) {

        if (loginUser != null) {
            Long cartId = cartService.addCart(loginUser.getId(), productId, count);
        }

        return "redirect:/carts";
    }

    // 장바구니 조회
    @GetMapping("/carts")
    public String cartList(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if (loginUser != null) { // 로그인한 사용자라면 장바구니 화면으로
            CartResponseDto carts = cartService.cartList(loginUser.getId());
            model.addAttribute("carts", carts);
            return "cart";
        } else { // 로그인하지 않은 사용자라면 로그인 화면으로
            return "redirect:/login";
        }
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
