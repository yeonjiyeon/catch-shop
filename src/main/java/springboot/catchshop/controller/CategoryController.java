package springboot.catchshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.dto.PageRequestDTO;
import springboot.catchshop.service.CategoryService;

import java.util.List;
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
    @GetMapping("/categories/{name}")
    @ResponseBody
    public void getCategory (@PathVariable String name, Model model) {
        model.addAttribute("result", categoryService.getCategory(name));
    }


    //카테고리 삭제
    @DeleteMapping("/categories/{name}")
    @ResponseBody
    public void deleteCategory (@PathVariable String name) {

    }

    //카테고리 수정


}
