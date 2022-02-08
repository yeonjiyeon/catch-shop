package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.catchshop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * Product entity
 * author:김지연
 */
@NoArgsConstructor
@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;//상품 번호

    @Column(name = "product_nm")
    private String name;

    @Column(name = "product_detail")
    private String text;

    private int price;
    private int stock;

    private String productImg;

    @Column(name = "product_reg")
    private Timestamp productReg;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category categories;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Product(Long id, String name){
        this.id = id;
        this.name = name;
    }
    //==비즈니스 로직==//
    /**
     * stock 증가 메서드
     */
    public void addStock(int quantity) {
        this.stock += stock;
    }

    
    /**
     * stock 감소 메서드
     */
    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stock = restStock;
    }
}
