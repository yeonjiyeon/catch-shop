package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.catchshop.dto.CartDto;

import javax.persistence.*;

// Cart Entity
// author: soohyun, last modified: 22.02.03

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int cartCount;

    // 생성 메서드
    @Builder
    public Cart(User user, Product product, int cartCount) {
        this.user = user;
        this.product = product;
        this.cartCount = cartCount;
    }

    // 수량 변경 메서드
    public void updateCartCount(int count) {
        this.cartCount = count;
    }
}
