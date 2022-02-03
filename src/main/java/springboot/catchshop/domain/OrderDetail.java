package springboot.catchshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue
    @Column(name = "orderdetail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "product_id")
    //private Product product;

    private int orderCount;
    private Long orderPrice;
}
