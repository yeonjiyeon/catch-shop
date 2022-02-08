package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.repository.CartRepository;
import springboot.catchshop.service.CartService;


@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;

    // 장바구니 조회
    @GetMapping("/cart")
    public String cartList() {
        return "cart";
    }
}
