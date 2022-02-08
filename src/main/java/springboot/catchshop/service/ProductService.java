package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.catchshop.domain.Product;
import springboot.catchshop.dto.ProductDTO;



public interface ProductService {
    //상품 등록
    Long addProduct(ProductDTO productDTO);
    default Product dtoToEntity(ProductDTO productDTO){
        Product entity = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
        return entity;
    }

    //상품 전체 조회
    
    //상품 개별 조회
    
    //상품 수정
    
    //상품 삭제
    
}
