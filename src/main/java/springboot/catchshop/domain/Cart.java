package springboot.catchshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    //private User user;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "product_id")
    //private Product product;

    private int cartCount;

    // 수량 변경
    public void updateCartCount(int count) {
        this.cartCount = count;
    }
}
