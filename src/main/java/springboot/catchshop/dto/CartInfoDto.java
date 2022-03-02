package springboot.catchshop.dto;

/**
 * Cart Info Dto (장바구니 정보를 담고 있는 Dto)
 * author: soohyun, last modified: 22.02.27
 */

import lombok.Data;
import lombok.Getter;
import springboot.catchshop.domain.Cart;

@Data
@Getter
public class CartInfoDto {

    private Long id;
    private String name; // 상품 이름
    private int price; // 상품 가격
    private String productImg; // 상품 이미지
    private int cartCount; // 상품 수량
    private Long totalProductPrice; // 상품 총 가격
    private Boolean underStock; // 재고수량 초과 여부

    // 생성 메서드
    public CartInfoDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.price = cart.getProduct().getPrice();
        this.productImg = cart.getProduct().getProductImg();
        this.cartCount = cart.getCartCount();
        this.totalProductPrice = Long.valueOf(price * cartCount);
        this.underStock = cartCount <= cart.getProduct().getStock() ? true : false;
    }
}
