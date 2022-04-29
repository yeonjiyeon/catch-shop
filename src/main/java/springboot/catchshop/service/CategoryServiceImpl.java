package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Category;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.repository.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    //카테고리 등록
    @Override
    public Long saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity();
        if (categoryDTO.getParent() == null){//부모 카테고리가 없는 경우
            if(!categoryRepository.existsByName(categoryDTO.getName())){
                throw new RuntimeException("해당 카테고리가 존재하지 않습니다.");
            }

            Category rootCategory = categoryRepository.findByName("fruit")
                    .orElseGet(() ->
                                    Category.builder()
                                            .name("fruit")
                                            .level(0)
                                            .build()
                            );
            if (!categoryRepository.existsByName("fruit")){
                categoryRepository.save(rootCategory);
            }
            category.changeParent(rootCategory);
            category.changeLevel(1);
        }else {//부모 카테고리가 있는 경우
            String parent = categoryDTO.getParent();
            Category parentCategory = categoryRepository.findByName(parent)
                    .orElseThrow(() -> new IllegalStateException("부모 카테고리 없음 예외"));
            category.changeParent(parentCategory);
            parentCategory.getChild().add(category);
        }

        return categoryRepository.save(category).getId();
    }


    //카테고리 조회
    @Override
    public List<CategoryDTO> getCategory(String name) {
        List<Category> categories = (List<Category>) categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음 예외"));

        List<CategoryDTO>  categoryDTOList = categories.stream().map(c -> new CategoryDTO(c)).collect(Collectors.toList());

        return categoryDTOList;


    }


    private Category findCategory (Long id) {
        return categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }





    //카테고리 삭제
    @Override
    public void deleteCategory(Long id) {
        Category category = findCategory(id);
        if (category.getChild().size() == 0){
            Category parentCategory = findCategory(category.getParent().getId());
            if (!parentCategory.getName().equals("fruit")){
                parentCategory.getChild().remove(category);
            }
            categoryRepository.deleteById(category.getId());
        }else {
            Category parentCategory = findCategory(category.getParent().getId());
            if (!parentCategory.getName().equals("fruit")){
                parentCategory.getChild().remove(category);
            }
            category.changeName("Deleted category");
        }



    }

    //카테고리 수정
    @Override
    public Long updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = findCategory(id);

        category.changeName(categoryDTO.getName());

        return category.getId();
    }




}
