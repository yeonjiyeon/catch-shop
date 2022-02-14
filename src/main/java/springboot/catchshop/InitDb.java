package springboot.catchshop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Address;
import springboot.catchshop.domain.Cart;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            Product product1 = createProduct("product1", 10000, 100);
            em.persist(product1);
            Product product2 = createProduct("product2", 10000, 100);
            em.persist(product2);
            Product product3 = createProduct("product3", 10000, 100);
            em.persist(product3);
            Product product4 = createProduct("product4", 10000, 100);
            em.persist(product4);
            Product product5 = createProduct("product5", 10000, 100);
            em.persist(product5);
            Product product6 = createProduct("product6", 10000, 100);
            em.persist(product6);
            Product product7 = createProduct("product7", 10000, 100);
            em.persist(product7);
            Product product8 = createProduct("product8", 10000, 100);
            em.persist(product8);
            Product product9 = createProduct("product9", 10000, 100);
            em.persist(product9);
            Product product10 = createProduct("product10", 10000, 100);
            em.persist(product10);
            Product product11 = createProduct("product11", 10000, 100);
            em.persist(product11);
            Product product12 = createProduct("product12", 10000, 100);
            em.persist(product12);

            // author: soohyun last modified: 22.02.13
            // 회원 데이터 생성
            Address address1 = new Address("road1", "detail1", "11111");
            User user1 = createUser("user1", "$2a$10$YsLNiiVMaVSpM0y8IW7uDOMeLcyRdetIzyXgw5bAx9YDDwsEqQmKe", "user1", "01012345678", address1);
            em.persist(user1);

            // 장바구니 데이터 생성
            Cart cart1 = createCart(product1, user1.getId(), 1);
            em.persist(cart1);
            Cart cart2 = createCart(product2, user1.getId(), 2);
            em.persist(cart2);
            Cart cart3 = createCart(product3, user1.getId(), 3);
            em.persist(cart3);
        }

        private Product createProduct(String name, int price, int stock) {
            Product product = new Product();
            product.changeName(name);
            product.changePrice(price);
            product.changeStock(stock);
            return product;
        }

        private User createUser(String loginId, String password, String name, String telephone, Address address) {

            User user = new User();
            user.setLoginId(loginId);
            user.setPassword(password);
            user.setName(name);
            user.setTelephone(telephone);
            user.setAddress(address);
            return user;
        }

        private Cart createCart(Product product, Long userId, int count) {
            Cart cart = new Cart(product, userId, count);
            return cart;
        }
    }
}

