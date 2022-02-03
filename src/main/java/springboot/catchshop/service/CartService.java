package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // 장바구니 수정
    @Transactional
    public Long update(Long id, int cartCount) {
        Cart cart = cartRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
        cart.updateCartCount(cartCount);

        return cart.getId();
    }
}
