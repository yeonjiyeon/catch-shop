package springboot.catchshop.dto;

import lombok.Data;
import lombok.Getter;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;

// Cart Dto
// author: soohyun, last modified: 22.02.13

@Data
@Getter
public class CartDto {

    private Long userId; // 로그인한 사용자 번호
    private Product product; // 상품
    private int cartCount; // 상품 수량

    public CartDto(Long userId, Product product, int cartCount) {
        this.userId = userId;
        this.product = product;
        this.cartCount = cartCount;
    }

    public Cart toEntity() {
        return new Cart(product, userId, cartCount);
    }
}
