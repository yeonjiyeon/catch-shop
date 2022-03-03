package springboot.catchshop.dto;

/**
 * Cart Info Dto (장바구니 정보를 담고 있는 Dto)
 * author: soohyun, last modified: 22.03.03
 */

import lombok.Data;
import springboot.catchshop.domain.Cart;

@Data
public class CartInfoDto {

    private Long id;
    private Long productId; // 상품 번호
    private String name; // 상품 이름
    private int price; // 상품 가격
    private String productImg; // 상품 이미지
    private int cartCount; // 상품 수량
    private Long totalProductPrice; // 상품 총 가격
    private Boolean underStock; // 재고수량 초과 여부

    // 생성 메서드
    public CartInfoDto(Cart cart) {
        this.id = cart.getId();
        this.productId = cart.getProduct().getId();
        this.name = cart.getProduct().getName();
        this.price = cart.getProduct().getPrice();
        this.productImg = cart.getProduct().getProductImg();
        this.cartCount = cart.getCartCount();
        this.totalProductPrice = Long.valueOf(price * cartCount);
        this.underStock = cartCount <= cart.getProduct().getStock() ? true : false;
    }
}
