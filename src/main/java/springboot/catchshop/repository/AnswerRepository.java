package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.catchshop.domain.Answer;

import java.util.Optional;

// Answer Repository
// author: 강수민, created: 21.02.01
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findById(Long id);
    Optional<Answer> findByQuestion(Long question_id);
}
