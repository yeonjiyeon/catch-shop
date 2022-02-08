package springboot.catchshop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Product;
import springboot.catchshop.repository.ProductRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Properties;
import java.util.stream.LongStream;


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
        }

        private Product createProduct(String name, int price, int stock) {
            Product product = new Product();
            product.changeName(name);
            product.changePrice(price);
            product.changeStock(stock);
            return product;
        }
    }
}

