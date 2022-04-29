package springboot.catchshop.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.admin.repository.AnswerRepository;
import springboot.catchshop.domain.Answer;
import springboot.catchshop.domain.Question;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.QuestionRepository;
import springboot.catchshop.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    // 답변 생성
    @Transactional
    public Answer createAnswer(User user, Long questionId, String content) {
        User writer = userRepository.findById(user.getId()).orElse(null);
        Question question = questionRepository.findById(questionId).orElse(null);
        assert question != null;
        assert writer != null;
        Answer answer = new Answer(writer, question, content);
        answerRepository.save(answer);

        question.updateAnswered();
        questionRepository.save(question);

        return answer;
    }

    // 답변 수정
    @Transactional
    public void updateAnswer(Answer answer, String content) {
        answer.updateAnswer(content);
        answerRepository.save(answer);
    }

    // 답변 삭제
    @Transactional
    public void deleteAnswer(Answer answer) {
        answerRepository.delete(answer);
    }
}
