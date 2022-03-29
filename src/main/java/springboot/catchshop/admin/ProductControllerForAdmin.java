package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.domain.Product;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.ProductRepository;
import springboot.catchshop.session.SessionConst;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

// 관리자용 Product Controller
// author: 강수민, created: 22.02.26
// last modified: 22.02.26
@Controller
@RequiredArgsConstructor
public class ProductControllerForAdmin {

    private final ProductServiceForAdmin productServiceForAdmin;
    private final ProductRepository productRepository;

    @GetMapping("/products/admin/{id}")
    public String getAllProducts(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                 @PathVariable("id") String userId, Model model) {
        if (loginUser == null) {
            return "login";
        }
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/products/admin")
    public String getOneProduct(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                @ModelAttribute("productDto") ProductDto dto) {
        if (loginUser == null) {
            return "login";
        }
        return "admin/create-product";
    }

    // 상품 등록
    @PostMapping("/products/admin")
    public String createProductForAdmin(@SessionAttribute(name = SessionConst.ROLE, required = false) String role,
                                        @ModelAttribute ProductDto dto) throws IOException {
        if (!Objects.equals(role, "ADMIN")) { // 권한없음
            return "admin/create-product";
        }
        productServiceForAdmin.saveProduct(dto);
        return "redirect:/";
    }

    // 상품 수정
    @GetMapping("/products/admin/{id}/edit")
    public String updateProductForAdmin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                                        @PathVariable("id") Long productId, Model model,
                                        @ModelAttribute("productDto") ProductDto dto) {
        if (loginUser == null) {
            return "login";
        }
        Product product = productRepository.findById(productId).orElse(null);
        model.addAttribute("product", product);
        return "admin/update-product";
    }

    @PutMapping("/products/admin/{id}/edit")
    public String updateProductForAdmin(@SessionAttribute(name = SessionConst.ROLE, required = false) String role,
                                        @PathVariable("id") Long productId,
                                        @ModelAttribute ProductDto dto) throws IOException {
        if (!Objects.equals(role, "ADMIN")) { // 권한없음
            return "admin/update-product";
        }
        productServiceForAdmin.updateProduct(productId, dto);
        return "redirect:/";
    }

    // 상품 삭제
    @DeleteMapping("/products/admin/{id}")
    public String deleteProductForAdmin(@SessionAttribute(name = SessionConst.ROLE, required = false) String role,
                                        @PathVariable("id") Long productId) {
        if (!Objects.equals(role, "ADMIN")) { // 권한없음
            return "admin/products";
        }

        productServiceForAdmin.deleteProduct(productId);
        return "redirect:/";
    }
}
