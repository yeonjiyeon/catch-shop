package springboot.catchshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.catchshop.domain.Product;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.dto.PageResultDTO;
import springboot.catchshop.dto.ProductDTO;

public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void 상품_등록(){
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .name("product1")
                .price(100000)
                .stock(100)
                .build();
    }

    @Test
    public void 상품_목록(){
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<ProductDTO, Product> resultDTO = productService.readProducts(pageRequestDTO);
        for(ProductDTO productDTO : resultDTO.getDtoList());


    }
}
