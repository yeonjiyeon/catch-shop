package springboot.catchshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.CartRepository;
import springboot.catchshop.repository.ProductRepository;
import springboot.catchshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// CartService Test
// author: soohyun, last modified: 22.02.04

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired CartRepository cartRepository;
    @Autowired CartService cartService;
    @Autowired UserRepository userRepository;
    @Autowired ProductRepository productRepository;

    private User user;
    private Product product;
    private int count = 1;

    @BeforeEach
    public void beforeEach() {
        user = new User();
        userRepository.save(user);
        product = new Product();
        productRepository.save(product);
    }

    @Test
    @DisplayName("장바구니 생성")
    public void addCart() throws Exception {

        // when
        Long addId = cartService.addCart(user.getId(), product.getId(), count);

        // then
        Optional<Cart> findCart = cartRepository.findById(addId);

        assertEquals(findCart.get().getId(), addId);
        assertEquals(findCart.get().getUser(), user);
        assertEquals(findCart.get().getProduct(), product);
        assertEquals(findCart.get().getCartCount(), count);
    }

    @Test
    @DisplayName("장바구니 조회")
    public void cartList() throws Exception {

        // given
        cartService.addCart(user.getId(), product.getId(), count);

        // when
        List<Cart> cartList = cartService.cartList();

        // then
        assertEquals(cartList.size(), 1);
    }

    @Test
    @DisplayName("장바구니 수정")
    public void updateCart() {

        // given
        Long cartId = cartService.addCart(user.getId(), product.getId(), count);

        // when
        Long updateId = cartService.updateCart(cartId, 2);

        // then
        Optional<Cart> findCart = cartRepository.findById(updateId);

        assertEquals(findCart.get().getId(), updateId);
        assertEquals(findCart.get().getUser(), user);
        assertEquals(findCart.get().getProduct(), product);
        assertEquals(findCart.get().getCartCount(), 2);
    }

    @Test
    @DisplayName("장바구니 삭제")
    public void deleteCart() {

        // given
        Long cartId = cartService.addCart(user.getId(), product.getId(), count);

        // when
        Long deleteId = cartService.deleteCart(cartId);

        // then
        Optional<Cart> findCart = cartRepository.findById(deleteId);

        assertEquals(findCart, Optional.empty());
    }
}