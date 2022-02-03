package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import springboot.catchshop.repository.OrderRepository;
import springboot.catchshop.service.OrderServcie;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderServcie orderServcie;
}
