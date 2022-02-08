package springboot.catchshop.dto;

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
    private int price;
    private int stock;
    private LocalDateTime regDate, modDate;
}
