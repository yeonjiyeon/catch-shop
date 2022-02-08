package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.catchshop.repository.OrderRepository;
import springboot.catchshop.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderServcie;

    // 주문하기
    @GetMapping("/order")
    public String order() {
        return "order";
    }
}
