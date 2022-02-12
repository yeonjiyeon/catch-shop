package springboot.catchshop.dto;

import lombok.Data;
import lombok.Getter;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;

// Cart Dto
// author: soohyun, last modified: 22.02.12

@Data
@Getter
public class CartDto {

    //private User user;
    private Long userId;
    private Product product;
    private int cartCount;

    public CartDto(Long userId, Product product, int cartCount) {
        this.userId = userId;
        this.product = product;
        this.cartCount = cartCount;
    }

    public Cart toEntity() {
        return new Cart(userId, product, cartCount);
    }
}
