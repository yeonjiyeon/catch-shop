package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.catchshop.domain.Product;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.dto.PageResultDTO;
import springboot.catchshop.dto.ProductDTO;
import springboot.catchshop.repository.ProductRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    //상품 등록
    @Override
    public Long addProduct(ProductDTO productDTO) {
        log.info("DTO====================");
        log.info(productDTO);

        Product entity = dtoToEntity(productDTO);
        log.info(entity);
        productRepository.save(entity);
        return entity.getId();
    }


    //상품 전체 조회
    @Override
    public PageResultDTO<ProductDTO, Product> readProducts(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Product> result = productRepository.findAll(pageable);

        Function<Product, ProductDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }




    //상품 개별 조회

    //상품 수정

    //상품 삭제
}
