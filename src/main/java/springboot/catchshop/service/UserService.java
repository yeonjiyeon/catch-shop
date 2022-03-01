package springboot.catchshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.catchshop.domain.User;
import springboot.catchshop.dto.UpdateUserDto;
import springboot.catchshop.repository.UserRepository;

import java.util.UUID;

// User Service
// author: 강수민, last modified: 22.02.08
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user) {
        // 중복 아이디 검증
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 아이디 중복 검증
     */
    private void validateDuplicateUser(User user) {
        userRepository.findByLoginId(user.getLoginId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 로그인
     * @return null이면 로그인 실패
     */
    @Transactional(readOnly = true)
    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElse(null);

    }

    /**
     * 아이디 찾기
     */
    @Transactional(readOnly = true)
    public User findId(String name, String telephone) {
        return userRepository.findByName(name)
                .filter(u -> u.getTelephone().equals(telephone))
                .orElse(null);
    }

    /**
     * 비밀번호 찾기
     */
    @Transactional(readOnly = true)
    public User findPw(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElse(null);
    }

    /**
     * 임시 비밀번호 생성 및 저장
     */
    @Transactional
    public String updatePw(User user) {
        String uuid = "";
        for (int i = 0; i < 5; i++) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = uuid.substring(0, 10);
        }
        String encodedPassword = passwordEncoder.encode(uuid);
        user.updatePassword(encodedPassword);
        return encodedPassword;
    }

    /**
     * 회원정보수정
     */
    @Transactional
    public void updateUser(User user, UpdateUserDto form) {
        user.updateUser(form);
        user.updatePassword(passwordEncoder.encode(form.getPassword()));
        // DB 반영
        userRepository.save(user);
    }
}
