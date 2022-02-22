package springboot.catchshop.dto;
/**
 * CategoryDTO
 * author:김지연
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String parent;
    private Map<String, CategoryDTO> children;
}
