package springboot.catchshop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.admin.ProductServiceForAdmin;
import springboot.catchshop.dto.QuestionDto;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class Question {

    @GeneratedValue
    @Id
    @Column(name = "question_id")
    private Long id;

    // fk
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id") // 작성자 번호
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id") // 상품 번호
    private Product product;

    private String category;
    private String contents;

    private String imgName; // 이미지명
    private String imgPath; // 이미지 경로

    private String secret; // 비밀글 여부

    private String password;
    private LocalDateTime date;

    private String answered; // 답변 여부

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    //==연관 관계 편의 메서드==//=
//    public void setUser(User user) {
//        this.user = user;
//        user.getQuestions().add(this);
//    }

//    public void setProduct(Product product) {
//        this.product = product;
//        product.getQuestions().add(this);
//    }

    //==생성 메서드==//
    public void setQuestion(User user, Product product, QuestionDto dto) {
//        this.setUser(user);
//        this.setProduct(product);
        this.user = user;
        this.product = product;
        this.category = dto.getCategory();
        this.contents = dto.getContents();

        if (dto.getSecret()) {
            this.secret = "비밀글";
            this.password = dto.getPassword();
        } else {
            this.secret = "공개글";
        }

        this.date = LocalDateTime.now();
        this.answered = "미답변";
    }

    public void updateImageInfo(String imgName) {
        this.imgName = imgName;
        this.imgPath = "/files/" + imgName;
    }

    public Question() {

    }

    public Question(User user, Product product, String category, String contents) {
        this.user = user;
        this.product = product;
        this.category =category;
        this.contents = contents;
    }
}
