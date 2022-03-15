package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springboot.catchshop.domain.Product;
import springboot.catchshop.repository.ProductRepository;
import springboot.catchshop.service.FileService;

import java.io.IOException;

// 관리자용 Product Service
// author: 강수민, created: 22.02.26
// last modified: 22.02.26
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceForAdmin {

    private final ProductRepository productRepository;
    private final FileService fileService;

    // 상품 생성
    public void saveProduct(ProductDto dto) throws IOException {
        // 상품 생성
        Product product = new Product();
        product.setProduct(dto);

        String imgName = fileService.uploadFile(dto.getProductImg()); // 이미지 파일 업로드
        product.updateImageInfo(imgName);

        productRepository.save(product);
    }

    // 상품 수정
    public void updateProduct(Long productId, ProductDto dto) throws IOException {
        Product product = productRepository.findById(productId).orElse(null);
        product.setProduct(dto);

        String imgName = fileService.uploadFile(dto.getProductImg());
        product.updateImageInfo(imgName);

        productRepository.save(product);
    }


}
