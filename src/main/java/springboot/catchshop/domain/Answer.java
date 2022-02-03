package springboot.catchshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Answer {

    @GeneratedValue
    @Id
    @Column(name = "answer_id")
    private Long id;

    // fk
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    // fk
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id") // 답변 작성자 번호
    private User user;

    private String contents;
    private LocalDateTime date;

    //==연관 관계 편의 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getAnswers().add(this);
    }

    //==생성 메서드==//
    public static Answer createAnswer(User user) {
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setDate(LocalDateTime.now());
        return answer;
    }

}
