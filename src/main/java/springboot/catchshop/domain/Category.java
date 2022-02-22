package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
/**
 * Category 기능
 * author:김지연
 */
@NoArgsConstructor
@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")//카테고리 번호
    private Long id;

    @Column(name = "category_nm")//카테고리명
    private String name;

    @OneToMany(mappedBy = "categories")//상품 번호
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @Builder
    public Category(String name, Category parent){
        this.name = name;
        this.parent = parent;
    }

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
