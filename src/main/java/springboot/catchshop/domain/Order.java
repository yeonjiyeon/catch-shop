package springboot.catchshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    //private User user;

    private String orderName;
    private String orderTel;
    private String orderAddr;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    // 주문 상태 변경
    public void updateOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }
}
