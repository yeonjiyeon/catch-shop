package springboot.catchshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    //카테고리 등록



    //카테고리 조회


    //카테고리 삭제


    //카테고리 수정

}
