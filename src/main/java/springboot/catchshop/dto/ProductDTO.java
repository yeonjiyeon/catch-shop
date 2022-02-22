package springboot.catchshop.dto;
/**
 * Product DTO
 * author:김지연
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;

    private String name;
    private String text;
    private int price;
    private int stock;
    private String productImg;
    private LocalDateTime regDate, modDate;
}
