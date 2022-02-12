package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.catchshop.dto.CartDto;

import javax.persistence.*;

// Cart Entity
// author: soohyun, last modified: 22.02.12

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int cartCount;

    // 생성 메서드
    public Cart(Long userId , Product product, int cartCount) {
        this.userId = userId;
        this.product = product;
        this.cartCount = cartCount;
    }


    // 수량 변경 메서드
    public void updateCartCount(int count) {
        this.cartCount = count;
    }
}
