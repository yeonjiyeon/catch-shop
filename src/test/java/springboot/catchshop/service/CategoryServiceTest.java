package springboot.catchshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.catchshop.repository.CategoryRepository;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

}
