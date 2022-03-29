package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.OrderStatus;
import springboot.catchshop.repository.OrderRepository;

@Service
@Transactional
@RequiredArgsConstructor
// 관리자용 Order Controller
// author: 강수민, created: 22.03.26
public class OrderServiceForAdmin {

    private final OrderRepository orderRepository;

    // 주문 취소
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalStateException("해당 주문이 존재하지 않습니다."));
        if (order.getOrderStatus() == OrderStatus.READY) {
            order.updateOrderStatus(OrderStatus.CANCEL);
        }
    }

    // 주문 상태 변경
    public void updateOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalStateException("해당 주문이 존재하지 않습니다."));
        // 준비 중이던 상품을 배송 완료로 변경
        if (order.getOrderStatus() == OrderStatus.READY) {
            order.updateOrderStatus(OrderStatus.DELIVERY);
        }
    }
}
