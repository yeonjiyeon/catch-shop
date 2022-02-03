package springboot.catchshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.form.JoinForm;
import springboot.catchshop.domain.User;
import springboot.catchshop.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    void 회원가입() throws Exception {
        // given
        User user = new User();
        JoinForm form = new JoinForm();
        form.setLoginId("kksswkd");
        user.setLoginId(form.getLoginId());

        // when
        Long savedId = userService.join(user);

        // then
        assertEquals(user, userRepository.findById(savedId).get());
    }

    @Test
    void 아이디중복확인() throws Exception {
        // given
        User user1 = new User();
        user1.setLoginId("kksswkd");

        User user2 = new User();
        user2.setLoginId("kksswkd");

        // when
        userService.join(user1);
        try {
            userService.join(user2);
        } catch (IllegalStateException e) {
            return;
        }

        // then
        fail("아이디 중복시 가입 불가");
    }

}