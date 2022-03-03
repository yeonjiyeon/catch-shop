package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.*;
import springboot.catchshop.dto.CartInfoDto;
import springboot.catchshop.dto.CartResponseDto;
import springboot.catchshop.dto.OrderRequestDto;
import springboot.catchshop.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Order Service
 * author: soohyun, last modified: 22.03.03
 */

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    // 주문 생성
    public Long createOrder(Order order, Long userId) {
        Order saveOrder = orderRepository.save(order); // 주문 생성
        List<Cart> carts = cartRepository.orderCartList(userId); // 주문 가능한 장바구니 목록 조회

        // 주문 상세 생성
        for(Cart cart: carts) {
            Product product = cart.getProduct();
            OrderDetail orderDetail = new OrderDetail(saveOrder, product, cart.getCartCount(), (long) product.getPrice());
            orderDetailRepository.save(orderDetail);
            product.removeStock(cart.getCartCount()); // 해당 상품 재고량 감소
        }

        return order.getId();
    }

    // 주문 수정
    public Long update(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        order.updateOrderStatus(status);

        return order.getId();
    }
}
