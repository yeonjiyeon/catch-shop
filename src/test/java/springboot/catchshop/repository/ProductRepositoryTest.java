package springboot.catchshop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.QProduct;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @AfterEach
    public void cleanup(){
        productRepository.deleteAll();
    }

    @Test
    public void 상품저장_테스트(){
        //given
        Long id = 1L;
        String name ="productA";

        productRepository.save(Product.builder()
                .id(id)
                .name(name)
                .build());

        //when
        List<Product> productList = productRepository.findAll();


        //then
        Product product = productList.get(0);
        Assertions.assertThat(product.getId()).isEqualTo(id);
        Assertions.assertThat(product.getName()).isEqualTo(name);


    }

    @Test
    public void 상품_더미데이터_넣기(){
        //given
        Long id = 1L;

        //when
        LongStream.rangeClosed(1, 100).forEach(i -> {
            Product product =Product.builder().id((Long)i).name("kim").build();
            productRepository.save(product);
        });

        List<Product> productList = productRepository.findAll();

        //then
        for(int i = 0; i < productList.size(); i++){
            Product product = productList.get(i);
            Assertions.assertThat(product.getId()).isEqualTo(id);
        }
    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        QProduct qProduct = QProduct.product;
        String keyWord = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qProduct.name.contains(keyWord);
        builder.and(expression);
       // Page<Product> result = productRepository.findAll();
    }



}
