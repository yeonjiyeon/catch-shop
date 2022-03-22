package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.catchshop.domain.Order;
import springboot.catchshop.repository.OrderRepository;
import springboot.catchshop.service.OrderService;

import java.util.List;

// 관리자용 Order Controller
// author: 강수민, created: 22.03.20
// last modified: 22.03.20
@Controller
@RequiredArgsConstructor
public class OrderControllerForAdmin {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    // 전체 주문 조회
    @GetMapping("/orders/admin")
    public String getAllOrdersForAdmin(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    // 주문 상세
    @GetMapping("/orders/{id}")
    public String getOrderDetails(Model model, @PathVariable("id") Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        model.addAttribute("order", order);
        return "admin/orderDetails";
    }

    // TODO 주문 취소, 상태 변경

    // 주문 취소
    @PatchMapping("/orders/{id}/admin")
    public String cancelOrder(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders/admin";
    }
}
