package springboot.catchshop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Order Detail Entity
 * author: soohyun, last modified: 22.03.03
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {

    @Id @GeneratedValue
    @Column(name = "orderdetail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문 내역

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 주문한 상품

    private int orderCount; // 주문 수량
    private Long orderPrice; // 주문 금액

    // 생성 메서드
    @Builder
    public OrderDetail(Order order, Product product, int orderCount, Long orderPrice) {
        this.order = order;
        this.product = product;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
    }
}
