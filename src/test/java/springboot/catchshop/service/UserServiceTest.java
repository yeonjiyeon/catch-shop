package springboot.catchshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.Role;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.JoinDto;
import springboot.catchshop.repository.UserRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    void 회원가입() throws Exception {
        // given
        JoinDto form = new JoinDto("userA", "A", "강유저", "010-1234-5678",
                "건강시 행복구 사랑동", "부자아파트", "12345",
                Role.USER, LocalDateTime.now());

        User user = form.toEntity();

        // when
        Long savedId = userService.join(user);

        // then
        assertEquals(user, userRepository.findById(savedId).get());
    }

    @Test
    void 아이디중복확인() throws Exception {
        // given
        JoinDto userA = new JoinDto("userA", "A", "강유저", "010-1234-5678",
                "건강시 행복구 사랑동", "부자아파트", "12345",
                Role.USER, LocalDateTime.now());

        JoinDto userB = new JoinDto("userA", "A", "강유저", "010-1234-5678",
                "건강시 행복구 사랑동", "부자아파트", "12345",
                Role.USER, LocalDateTime.now());

        // when
        userService.join(userA.toEntity());
        try {
            userService.join(userB.toEntity());
        } catch (IllegalStateException e) {
            return;
        }

        // then
        fail("아이디 중복시 가입 불가");
    }

}