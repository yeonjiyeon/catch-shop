package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.OrderDetail;
import springboot.catchshop.domain.OrderStatus;
import springboot.catchshop.repository.OrderDetailRepository;
import springboot.catchshop.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServcie {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    // 주문 생성
    @Transactional
    public void create(Order order, List<OrderDetail> orderDetails) {
        orderRepository.save(order);

        for(OrderDetail orderDetail: orderDetails) {
            orderDetailRepository.save(orderDetail);
        }
    }

    // 주문 수정
    @Transactional
    public Long update(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        order.updateOrderStatus(status);

        return order.getId();
    }
}
