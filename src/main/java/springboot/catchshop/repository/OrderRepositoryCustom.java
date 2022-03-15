package springboot.catchshop.repository;

import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;

import java.util.List;

/**
 * Order Repository Custom
 * author: soohyun, last modified: 22.03.11
 */
public interface OrderRepositoryCustom {

    // 로그인한 사용자로 주문 조회
    List<Order> orderList(User user);
}
