package springboot.catchshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.catchshop.domain.User;

import java.util.List;
import java.util.Optional;

// User Repository
// author: 강수민, created: 21.02.01
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLoginId(String loginId);
    List<User> findAll();
}
