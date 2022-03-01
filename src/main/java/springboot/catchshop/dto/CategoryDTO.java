package springboot.catchshop.dto;
/**
 * CategoryDTO
 * author:김지연
 */
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.catchshop.domain.Category;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String branch;
    private String name;
    private String parent;
    private Integer level;
    private Map<String, CategoryDTO> children;

    //생성자에서 entity-> dto처리
    public CategoryDTO(Category entity){
        this.id = entity.getId();
        this.branch = entity.getBranch();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParent() == null){
            this.parent = "과일";
        }else{
            this.parent = entity.getParent().getName();
        }

        this.children = entity.getChild() == null? null :
                entity.getChild().stream().collect(Collectors.toMap(Category::getName, CategoryDTO::new));
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .level(level)
                .name(name)
                .build();
    }
}
