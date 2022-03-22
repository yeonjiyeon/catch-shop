package springboot.catchshop;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

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
        private final PasswordEncoder passwordEncoder;

        public void dbInit() {
//            Category fruit = createCategory("fruit", 0);
//            em.persist(fruit);
            Category strawberry = createCategory("strawberry",1);
            em.persist(strawberry);
            Category berry = createCategory("berry", 1);
            em.persist(berry);
            Category lemon = createCategory("lemon",1);
            em.persist(lemon);
            Category kiwi = createCategory("kiwi",1);
            em.persist(kiwi);



            Product product1 = createProduct("product1", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100,strawberry);
            em.persist(product1);
            Product product2 = createProduct("product2", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100,berry);
            em.persist(product2);
            Product product3 = createProduct("product3", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product3);
            Product product4 = createProduct("product4", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product4);
            Product product5 = createProduct("product5", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100,strawberry);
            em.persist(product5);
            Product product6 = createProduct("product6", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product6);
            Product product7 = createProduct("product7", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100,berry);
            em.persist(product7);
            Product product8 = createProduct("product8", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product8);
            Product product9 = createProduct("product9", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100, strawberry);
            em.persist(product9);
            Product product10 = createProduct("product10", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100, berry);
            em.persist(product10);
            Product product11 = createProduct("product11", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product11);
            Product product12 = createProduct("product12", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product12);
            Product product13 = createProduct("product13", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100,strawberry);
            em.persist(product13);
            Product product14 = createProduct("product14", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100, berry);
            em.persist(product14);
            Product product15 = createProduct("product15", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product15);
            Product product16 = createProduct("product16", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product16);
            Product product17 = createProduct("product17", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100,strawberry);
            em.persist(product17);
            Product product18 = createProduct("product18", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product18);
            Product product19 = createProduct("product19", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100,berry);
            em.persist(product19);
            Product product20 = createProduct("product20", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product20);
            Product product21 = createProduct("product21", "/assets/img/products/product-img-1.jpg", "/assets/img/products/product-img-1.jpg", 10000, 100,strawberry);
            em.persist(product21);
            Product product22 = createProduct("product22", "/assets/img/products/product-img-2.jpg", "/assets/img/products/product-img-2.jpg", 10000, 100,berry);
            em.persist(product22);
            Product product23 = createProduct("product23", "/assets/img/products/product-img-3.jpg", "/assets/img/products/product-img-3.jpg", 10000, 100,lemon);
            em.persist(product23);
            Product product24 = createProduct("product24", "/assets/img/products/product-img-4.jpg", "/assets/img/products/product-img-4.jpg", 10000, 100,kiwi);
            em.persist(product24);

            // author: soohyun last modified: 22.02.19
            // 회원 데이터 생성
            Address address1 = new Address("road1", "detail1", "11111");
            User user1 = createUser("user1", passwordEncoder.encode("1"), "user1", "01012345678",
                    address1, Role.USER.toString(), LocalDateTime.now());
            em.persist(user1);

            // author: 강수민 created: 22.02.23
            // 관리자 데이터 생성
            Address address2 = new Address("road2", "detail2", "22222");
            User admin1 = createUser("admin1", passwordEncoder.encode("1"), "admin1", "01012345678",
                    address2, Role.ADMIN.toString(), LocalDateTime.now());
            em.persist(admin1);

            // author: soohyun last modified: 22.03.05
            // 장바구니 데이터 생성
            Cart cart1 = createCart(product1, user1.getId(), 1);
            em.persist(cart1);
            Cart cart2 = createCart(product2, user1.getId(), 2);
            em.persist(cart2);
            Cart cart3 = createCart(product3, user1.getId(), 300);
            em.persist(cart3);

            // author: 강수민 created: 22.03.15
            // question 데이터 생성
            Question question1 = createQuestion(user1, product24, "상품문의", "상품 문의입니다", "secret", "답변 완료");
            em.persist(question1);
            Question question2 = createQuestion(user1, product24, "배송문의", "배송 문의입니다", "secret", "미답변");
            em.persist(question2);
            Question question3 = createQuestion(user1, product24, "주문문의", "주문 문의입니다", "secret", "답변 완료");
            em.persist(question3);
            Question question4 = createQuestion(user1, product23, "상품문의", "상품 문의입니다", "secret", "답변 완료");
            em.persist(question4);
            Question question5 = createQuestion(user1, product23, "배송문의", "배송 문의입니다", "secret", "답변 완료");
            em.persist(question5);

            // author: 강수민 created: 22.03.16
            // answer 데이터 생성
            Answer answer1 = createAnswer(admin1, question1, "답변입니다.");
            em.persist(answer1);
            Answer answer2 = createAnswer(admin1, question1, "답변입니다.");
            em.persist(answer2);
            Answer answer3 = createAnswer(admin1, question1, "답변입니다.");
            em.persist(answer3);
            Answer answer4 = createAnswer(admin1, question3, "답변입니다.");
            em.persist(answer4);
            Answer answer5 = createAnswer(admin1, question4, "답변입니다.");
            em.persist(answer5);
            Answer answer6 = createAnswer(admin1, question5, "답변입니다.");
            em.persist(answer6);
            Answer answer7 = createAnswer(admin1, question5, "답변입니다.");
            em.persist(answer7);



            // author: soohyun last modified: 22.03.19
            // 주문 데이터 생성
            Order order1 = createOrder(user1, user1.getName(), user1.getTelephone(), user1.getAddress(), 30000L, 3000L);
            em.persist(order1);
            OrderDetail orderDetail1 = createOrderDetail(order1, product1, 1, (long) product1.getPrice());
            em.persist(orderDetail1);
            OrderDetail orderDetail2 = createOrderDetail(order1, product2, 2, (long) product2.getPrice());
            em.persist(orderDetail2);

            Order order2 = createOrder(user1, user1.getName(), user1.getTelephone(), user1.getAddress(), 70000L, 0L);
            order2.updateOrderStatus(OrderStatus.DELIVERY);
            em.persist(order2);
            OrderDetail orderDetail3 = createOrderDetail(order2, product3, 3, (long) product3.getPrice());
            em.persist(orderDetail3);
            OrderDetail orderDetail4 = createOrderDetail(order2, product4, 4, (long) product4.getPrice());
            em.persist(orderDetail4);
        }

        // modified by 강수민, 22.02.26 - 상품이미지 경로 추가
        private Product createProduct(String name, String productImg, String productImgPath, int price, int stock, Category category) {
            Product product = new Product();
            product.changeName(name);
            product.changePrice(price);
            product.changeStock(stock);
            product.changeProductImg(productImg);
            product.changeProductImgPath(productImgPath);
            product.changeCategory(category);
            return product;
        }

        // modified by 강수민, 22.02.15 - setter 삭제
        private User createUser(String loginId, String password, String name, String telephone,
                                Address address, String role, LocalDateTime joindate) {

            User user = new User(loginId, password, name, telephone,
                    address, role, joindate);
            return user;
        }

        private Cart createCart(Product product, Long userId, int count) {
            Cart cart = new Cart(product, userId, count);
            return cart;
        }

        private Category createCategory(String name, Integer level) {
            Category category = Category.builder()
                    .name(name)
                    .level(level)
                    .build();
            return category;
        }
      
        private Question createQuestion(User user, Product product, String category, String contents,
                                        String secret, String answered) {
            Question question = new Question(user, product, category, contents, secret, answered);
            return question;
        }

        private Answer createAnswer(User user, Question question, String contents) {
            Answer answer = new Answer(user, question, contents);
            return answer;
        }

        private Order createOrder(User user, String orderName, String orderTel, Address address, Long totalPrice, Long shippingFee) {
            Order order = new Order(user, orderName, orderTel, address, totalPrice, shippingFee);
            return order;
        }

        private OrderDetail createOrderDetail(Order order, Product product, int orderCount, Long orderPrice) {
            OrderDetail orderDetail = new OrderDetail(order, product, orderCount, orderPrice);
            return orderDetail;
        }

    }

//    private Category createCategory1(String name, Category parent, Integer level) {
//        Category category = Category.builder()
//                .name(name)
//                .parent(parent)
//                .level(level)
//                .build();
//        return category;
//    }
}

