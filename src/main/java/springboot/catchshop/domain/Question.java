package springboot.catchshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@Entity
public class Question {

    @GeneratedValue
    @Id
    @Column(name = "question_id")
    private Long id;

    // fk
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id") // 질문 작성자 번호
    private User user;

    private QuestionCategory category;
    private String contents;

    @Enumerated(value = EnumType.STRING)
    private QuestionStatus secret; // 비밀글 여부

    private String password;
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private QuestionStatus answered; // 답변 여부

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    //==연관 관계 편의 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getQuestions().add(this);
    }

    //==생성 메서드==//
    public static Question createQuestion(User user) {
        Question question = new Question();
        question.setUser(user);
        question.setDate(LocalDateTime.now());
        return question;
    }
}
