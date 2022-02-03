package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.catchshop.repository.QuestionRepository;
import springboot.catchshop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    /**
     * 질문 생성 동적 쿼리
     */
//    public Long Question(Long userid) {
//        // 엔티티 조회
//        User user = userRepository.findById(userid);
//        Question question = Question.createQuestion(user);
//    }


}
