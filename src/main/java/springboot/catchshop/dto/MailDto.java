package springboot.catchshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailDto {

    private String address;
    private String title;
    private String message;
}
