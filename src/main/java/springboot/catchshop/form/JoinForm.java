package springboot.catchshop.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class JoinForm {
    @NotEmpty(message = "필수 항목입니다.")
    private String loginId;

    @NotEmpty(message = "필수 항목입니다.")
    private String password;

    @NotEmpty(message = "필수 항목입니다.")
    private String name;

    @NotEmpty(message = "필수 항목입니다.")
    private String telephone;

    @NotEmpty(message = "필수 항목입니다.")
    private String road;

    @NotEmpty(message = "필수 항목입니다.")
    private String detail;

    @NotEmpty(message = "필수 항목입니다.")
    private String postalcode;
}
