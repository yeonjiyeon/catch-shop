package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.service.CartService;

// Cart Controller
// author: soohyun, last modified: 22.02.03

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("/cart")
    public void addCart() {

    }

    // 장바구니 조회
    @GetMapping("/cart")
    public String cartList() {
        return "cart";
    }

    // 장바구니 변경
    @PutMapping("/cart/{cartId}")
    public void updateCart() {

    }

    // 장바구니 삭제
    @DeleteMapping("cart/{cartId}")
    public void deleteCart() {

    }
}
