package springboot.catchshop.dto;

// CartList Dto (조회시 장바구니 목록에 필요한 Dto)
// author: soohyun, last modified: 22.02.14

import lombok.Data;
import lombok.Getter;
import springboot.catchshop.domain.Cart;

@Data
@Getter
public class CartListDto {

    private Long id;
    private String name; // 상품 이름
    private int price; // 상품 가격
    private int cartCount; // 상품 수량
    private Long totalProductPrice; // 상품 총 가격

    // 생성 메서드
    public CartListDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.price = cart.getProduct().getPrice();
        this.cartCount = cart.getCartCount();
        this.totalProductPrice = Long.valueOf(price * cartCount);
    }
}
