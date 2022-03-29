package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.OrderDetail;
import springboot.catchshop.repository.OrderRepository;
import springboot.catchshop.service.OrderService;

import java.util.List;

import static springboot.catchshop.domain.QOrder.order;

// 관리자용 Order Controller
// author: 강수민, created: 22.03.20
// last modified: 22.03.20
// author: soohyun, last modified: 22.03.26

@Controller
@RequiredArgsConstructor
public class OrderControllerForAdmin {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderServiceForAdmin orderServiceForAdmin;

    // 전체 주문 조회
    @GetMapping("/orders/admin")
    public String orderListForAdmin(Model model) {
        List<Order> orders = orderRepository.orderListForAdmin();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    // 주문 상세
    @GetMapping("/orders/{id}/admin")
    public String orderDetailsForAdmin(Model model, @PathVariable("id") Long orderId) {
        List<OrderDetail> orderDetails = orderRepository.orderDetailListForAdmin(orderId);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/orderDetails";
    }
//     public String getOrderDetails(Model model, @PathVariable("id") Long orderId) {
//         Order order = orderRepository.findById(orderId).orElse(null);
//         model.addAttribute("order", order);
//         return "admin/orderDetails";
//     }

//     // 주문 취소
//     @PatchMapping("/orders/{id}/admin")
//     public String cancelOrderForAdmin(@PathVariable("id") Long orderId) {
//         orderServiceForAdmin.cancelOrder(orderId);
//         return "redirect:/orders/admin";
//     }

//     // 주문 상태 변경
//     @PatchMapping("/orders/{id}/admin/status")
//     public String updateOrderStatusForAdmin(@PathVariable("id") Long orderId) {
//         orderServiceForAdmin.updateOrderStatus(orderId);
//         return "redirect:/orders/admin";
//     }

    // 주문 취소
    @PatchMapping("/orders/{id}/admin")
    public String cancelOrderForAdmin(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders/admin";
    }
}
