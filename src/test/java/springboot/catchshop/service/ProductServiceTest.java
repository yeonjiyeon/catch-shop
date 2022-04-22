package springboot.catchshop.service;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Category;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.ProductStatus;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.dto.PageResultDTO;
import springboot.catchshop.dto.ProductDTO;
import springboot.catchshop.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void 상품_등록(){
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .id(10001L)
                .name("product1001")
                .price(100000)
                .stock(100)
                .build();


        //when
        //Long saveId = productService.addProduct(productDTO);

        //then
        assertThat(productService.addProduct(productDTO)).isEqualTo(10001L);

    }

    @Test
    public void 상품_목록_페이징(){
        //given
        for(Long i = 201L; i< 300; i++){
            Product.builder().id(i).build();
        }
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        //when
        PageResultDTO<ProductDTO, Object[]> resultDTO = productService.readProductsWithProductStatus(pageRequestDTO, null);

        //then
        for(ProductDTO productDTO : resultDTO.getDtoList()){
            System.out.println("productDTO" + productDTO);
        };
    }


    @Test
    public void 상품_수정(){
        //given
        ProductDTO productDTO = ProductDTO.builder()
            .id(10001L)
            .name("product1001")
            .price(100000)
            .stock(100)
            .build();


        //when


        //then

    }

    @Test
    public void 상품_삭제(){
        //given
        ProductDTO productDTO = ProductDTO.builder()
            .id(10001L)
            .name("product1001")
            .price(100000)
            .stock(100)
            .build();

        //when


        //then

    }
    
    
}
