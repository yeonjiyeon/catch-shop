package springboot.catchshop.dto;

import lombok.Data;
import lombok.Getter;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;

// Cart Dto
// author: soohyun, last modified: 22.02.03

@Data
@Getter
public class CartDto {

    private User user;
    private Product product;
    private int cartCount;

    public CartDto(User user, Product product, int cartCount) {
        this.user = user;
        this.product = product;
        this.cartCount = cartCount;
    }

    public Cart toEntity() {
        return Cart.builder()
                .user(user)
                .product(product)
                .cartCount(cartCount)
                .build();
    }
}
