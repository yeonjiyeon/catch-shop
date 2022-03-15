package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import springboot.catchshop.dto.UpdateUserDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "C_USER")
public class User {

    @GeneratedValue
    @Id
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String telephone;

    @Embedded
    private Address address;

    private LocalDateTime joindate;
    private String role;

//    @OneToMany(mappedBy = "user")
//    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    @Builder
    public User(String loginId, String password, String name, String telephone,
                Address address, String role, LocalDateTime joindate) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
        this.joindate = joindate;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUser(UpdateUserDto dto) {
        this.telephone = dto.getTelephone();
        this.address = new Address(dto.getRoad(), dto.getDetail(), dto.getPostalcode());
    }

}
