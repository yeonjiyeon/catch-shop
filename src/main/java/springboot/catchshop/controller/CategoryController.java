package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.service.CategoryService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/categories")
    @ResponseBody
    //카테고리 등록
    public Long savaCategory(CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    //카테고리 조회
    @GetMapping("/categories/{branch}")
    @ResponseBody
    public void getCategoryByBranch (@PathVariable String branch) {

        //eturn categoryService.getCategoryByBranch(branch);
    }

    //카테고리 삭제
    @DeleteMapping("/categories/{branch}")
    @ResponseBody
    public void deleteCategory (@PathVariable String branch) {

    }

    //카테고리 수정


}
