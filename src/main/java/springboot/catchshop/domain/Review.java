package springboot.catchshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
/**
 * Review 기능
 * author:김지연
 */
@NoArgsConstructor
@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_contents")
    private String contents;


    @Column(name = "review_img")
    private String img;

    @Column(name = "review_star")
    private String star;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
