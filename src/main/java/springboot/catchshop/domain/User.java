package springboot.catchshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
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

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    @Builder
    public User(String loginId, String password, String name, String telephone,
                   String road, String detail, String postalcode,
                Role role, LocalDateTime joindate) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.address = new Address(road, detail, postalcode);
        this.role = role;
        this.joindate = joindate;
    }

}
