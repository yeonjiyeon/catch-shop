package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springboot.catchshop.domain.Product;
import springboot.catchshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

// 관리자용 Product Service
// author: 강수민, created: 22.02.26
// last modified: 22.02.26
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceForAdmin {

    private final ProductRepository productRepository;

    // 상품 생성
    public void saveProduct(ProductDto dto) throws IOException {
        MultipartFile imgFile = dto.getProductImg(); // 이미지 파일
        String productImgName = uploadImg(imgFile); // 이미지 파일 업로드

        // 상품 생성
        Product product = new Product();
        product.setProduct(dto, productImgName,"/files/"+productImgName);
        productRepository.save(product);
    }

    // 상품 수정
    public void updateProduct(Long productId, ProductDto dto) throws IOException {
        Product product = productRepository.findById(productId).orElse(null);
        String productImgName = uploadImg(dto.getProductImg());
        product.setProduct(dto, productImgName, "/files/"+productImgName);
        productRepository.save(product);
    }

    // 이미지 업로드 메서드
    public String uploadImg(MultipartFile imgFile) throws IOException {
        String originalName = imgFile.getOriginalFilename(); // 파일명

        // 파일 저장 경로
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";

        // 랜덤 파일명 생성
        UUID uuid = UUID.randomUUID();
        String productImgName = uuid + "_" + originalName;

        // 파일 저장
        File file = new File(projectPath, productImgName);
        imgFile.transferTo(file);

        return productImgName;
    }
}
