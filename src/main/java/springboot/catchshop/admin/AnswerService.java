package springboot.catchshop.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Answer;
import springboot.catchshop.domain.Question;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.QuestionRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    // 답변 생성
    @Transactional
    public Answer save(User user, Long questionId, AnswerDto dto) {
        Answer answer = new Answer();
        Question question = questionRepository.findById(questionId).orElse(null);
        answer.setAnswer(user, question, dto.getContents());
        answerRepository.save(answer);

        question.updateAnswered();
        questionRepository.save(question);

        return answer;
    }

    // 답변 수정
    @Transactional
    public void updateAnswer(Long answerId, AnswerDto dto) {
        Answer answer = answerRepository.findById(answerId).orElse(null);
        answer.updateAnswer(dto.getContents());
        answerRepository.save(answer);
    }
}
