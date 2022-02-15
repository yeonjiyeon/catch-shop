package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;
import springboot.catchshop.dto.CartDto;
import springboot.catchshop.dto.CartListDto;
import springboot.catchshop.dto.CartResponseDto;
import springboot.catchshop.repository.CartRepository;
import springboot.catchshop.repository.ProductRepository;
import springboot.catchshop.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

// Cart Service
// author: soohyun, last modified: 22.02.14

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // 장바구니 생성
    public Long addCart(Long userId, Long productId, int count) {
        userRepository.findById(userId).orElseThrow( () -> new IllegalStateException("회원이 존재하지 않습니다."));
        Product product = productRepository.findById(productId).orElseThrow( () -> new IllegalStateException("상품이 존재하지 않습니다."));

        CartDto cartDto = new CartDto(userId, product, count);
        Cart saveCart = cartRepository.save(cartDto.toEntity()); // 장바구니 생성

        return saveCart.getId();
    }

    // 장바구니 목록 조회
    @Transactional(readOnly = true)
    public CartResponseDto cartList(Long userId) {
        userRepository.findById(userId).orElseThrow( () -> new IllegalStateException("회원이 존재하지 않습니다."));

        List<Cart> carts = cartRepository.findByUserId(userId); // 장바구니 목록 조회
        List<CartListDto> cartList = carts.stream().map(c -> new CartListDto(c)).collect(Collectors.toList());
        CartResponseDto cartResponseDto = new CartResponseDto(cartList); // 장바구니 관련 정보 조회

        return cartResponseDto;
    }

    // 장바구니 수정
    public Long updateCart(Long id, int cartCount) {
        Cart cart = cartRepository.findById(id).orElseThrow( () -> new IllegalStateException("장바구니가 존재하지 않습니다."));
        cart.updateCartCount(cartCount);

        return cart.getId();
    }

    // 장바구니 삭제
    public Long deleteCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow( () -> new IllegalStateException("장바구니가 존재하지 않습니다."));
        cartRepository.delete(cart);

        return cart.getId();
    }
}
