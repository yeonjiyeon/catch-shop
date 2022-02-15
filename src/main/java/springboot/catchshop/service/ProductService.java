package springboot.catchshop.service;
/**
 * ProductService
 * author:김지연
 */
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.catchshop.domain.Product;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.dto.PageResultDTO;
import springboot.catchshop.dto.ProductDTO;



public interface ProductService {
    //dtoToEntity
    default Product dtoToEntity(ProductDTO productDTO){
        Product entity = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .text(productDTO.getText())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .productImg(productDTO.getProductImg())
                .build();
        return entity;
    }

    default ProductDTO entityToDto(Product product){
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .text(product.getText())
                .price(product.getPrice())
                .stock(product.getStock())
                .productImg(product.getProductImg())
                .build();

        return dto;
    }

    //상품 등록
    Long addProduct(ProductDTO productDTO);

    //상품 전체 조회
    PageResultDTO<ProductDTO, Product> readProducts(PageRequestDTO requestDTO);

    //상품 개별 조회
    
    //상품 수정
    
    //상품 삭제
    
}
