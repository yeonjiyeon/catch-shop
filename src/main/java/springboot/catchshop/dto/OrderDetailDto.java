package springboot.catchshop.dto;

import lombok.Data;
import springboot.catchshop.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderDetail Response Dto (조회시 주문 상세 전체 정보를 담고 있는 Dto)
 * author: soohyun, last modified: 22.03.11
 */

@Data
public class OrderDetailDto {

    private Long id;
    private Product product; // 주문 상품
    private int orderCount; // 주문 수량
    private Long orderPrice; // 주문 금액

    public OrderDetailDto(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.product = orderDetail.getProduct();
        this.orderCount = orderDetail.getOrderCount();
        this.orderPrice = orderDetail.getOrderPrice();
    }
}
