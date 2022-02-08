package springboot.catchshop.domain;

import lombok.*;
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
@AllArgsConstructor
@Entity
@Getter
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "product_id")//상품 번호
    private Long id;

    @Column(name = "product_nm")//상품 이름
    private String name;

    @Column(name = "product_detail")//상품 번호 상세정보
    private String text;

    private int price;//상품 가격
    private int stock;//상품 수량

    private String productImg;//상품 이미지

//    @Column(name = "product_reg")
//    private Timestamp productReg;//등록일 -> baseEntitiy사용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")//카테고리 
    private Category categories;

    @OneToMany(mappedBy = "product")//상품
    private List<Review> reviews = new ArrayList<>();


    @Builder
    public Product(Long id,String name, int price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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


    //=====값 수정 메서드(임시)
    public void changeName(String name){
        this.name = name;
    }

    public void changePrice(int price){
        this.price = price;
    }

    public void changeStock(int stock){
        this.stock = stock;
    }
}
