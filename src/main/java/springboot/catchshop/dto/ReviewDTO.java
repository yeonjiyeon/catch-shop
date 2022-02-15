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
public class ReviewDTO {
    private Long id;

    private String contents;

    private String img;

    private String star;

    private LocalDateTime regDate, modDate;
}
