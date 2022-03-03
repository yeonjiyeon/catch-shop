package springboot.catchshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.catchshop.domain.Address;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;

import javax.validation.constraints.NotEmpty;

/**
 * Order Request Dto (주문시 주문 정보를 담고 있는 Dto)
 * author: soohyun, last modified: 22.03.02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotEmpty(message = "이름을 입력하세요.")
    private String name; // 수령자 이름

    @NotEmpty(message = "전화번호를 입력하세요.")
    private String telephone; // 수령자 전화번호

    // 수령지 주소
    @NotEmpty(message = "우편 번호를 입력하세요.")
    private String postalcode;

    @NotEmpty(message = "도로명 주소를 입력하세요.")
    private String road;

    @NotEmpty(message = "상세 주소를 입력하세요.")
    private String detail;

    public Order toEntity(User user) {
        Address address = new Address(road, detail, postalcode);

        return Order.builder()
                .user(user)
                .orderName(name)
                .orderTel(telephone)
                .address(address)
                .build();
    }

}
