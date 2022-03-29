package springboot.catchshop.dto;
/**
 * CategoryDTO
 * author:김지연
 */
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.catchshop.domain.Category;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String parent;
    private Integer level;
    private List<CategoryDTO> children;

    //생성자에서 entity-> dto처리
    public CategoryDTO(Category entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParent() == null){
            this.parent = "fruit";
        }else{
            this.parent = entity.getParent().getName();//부모가 과일 아니면 다음 level의 카테고리명으로 이동
        }

        this.children = entity.getChild() == null? null :
                entity.getChild().stream().map(c -> new CategoryDTO(c)).collect(Collectors.toList());
    }


    public Category toEntity () {
        return Category.builder()
                .level(level)
                .name(name)
                .build();
    }
}
