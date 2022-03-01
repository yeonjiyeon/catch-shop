package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Category;
import springboot.catchshop.dto.CategoryDTO;
import springboot.catchshop.repository.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    //카테고리 등록
    @Override
    public Long saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity();
        if (categoryDTO.getParent() == null){
            if(categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())){
                throw new RuntimeException("branch와 name이 같을 수 없습니다.");
            }

            Category rootCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), "ROOT")
                    .orElseGet(() ->
                                    Category.builder()
                                            .name("ROOT")
                                            .branch(categoryDTO.getBranch())
                                            .level(0)
                                            .build()
                            );
            if (!categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")){
                categoryRepository.save(rootCategory);
            }
            category.setParent(rootCategory);
            category.setLevel(1);
        }else {
            String parent = categoryDTO.getParent();
            Category parentCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), parent)
                    .orElseThrow(() -> new IllegalStateException("부모 카테고리 없음 예외"));
            category.setParent(parentCategory);
            parentCategory.getChild().add(category);
        }

        return categoryRepository.save(category).getId();
    }


    //카테고리 조회
    @Override
    public Map<String, CategoryDTO> getCategoryByBranch(String branch) {
        Category category = categoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map <String, CategoryDTO> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
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
            if (!parentCategory.getName().equals("ROOT")){
                parentCategory.getChild().remove(category);
            }
            categoryRepository.deleteById(category.getId());
        }else {
            Category parentCategory = findCategory(category.getParent().getId());
            if (!parentCategory.getName().equals("ROOT")){
                parentCategory.getChild().remove(category);
            }
            category.setName("Deleted category");
        }



    }

    //카테고리 수정
    @Override
    public Long updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = findCategory(id);

        category.setName(categoryDTO.getName());

        return category.getId();
    }




}
