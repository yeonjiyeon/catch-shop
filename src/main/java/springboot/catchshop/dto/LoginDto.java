package springboot.catchshop.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

// 로그인 폼
// author: 강수민, last modified: 21.02.08
@Data
public class LoginDto {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    public LoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
