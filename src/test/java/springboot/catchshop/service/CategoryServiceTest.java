package springboot.catchshop.service;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.catchshop.domain.Address;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    private Product product;

    @BeforeEach
    public void beforeEach() {
        // 상품 생성
        product = new Product();
        product.changeName("product10000000");
        product.changePrice(10000);
        product.changeStock(10);
        em.persist(product);
    }



    //카테고리 등록

    @Test
    public void 카테고리_등록(){

    }


    //카테고리 조회
    @Test
    public void 카테고리_조회(){}

    //카테고리 수정
    @Test
    public void 카테고리_수정(){}

    //카테고리 삭제
    @Test
    public void 카테고리_삭제(){}


}
