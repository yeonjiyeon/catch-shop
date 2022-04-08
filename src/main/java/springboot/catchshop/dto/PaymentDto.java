package springboot.catchshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.catchshop.domain.Order;
import springboot.catchshop.domain.User;

/**
 * Payment Dto (결제 정보를 담고 있는 Dto)
 * author: soohyun, last modified: 22.04.02
 */

@Data
public class PaymentDto {

    private String imp_uid; // 결제 번호
    private String buyer_name; // 구매자 이름
    private String buyer_tel; // 구매자 번호
    private String buyer_postcode; // 구매자 우편번호
    private String buyer_addr; // 구매자 주소

    public Order toEntity(User user, Long totalPrice, Long shippingFee) {
        return Order.builder()
                .payment_id(imp_uid)
                .user(user)
                .orderName(buyer_name)
                .orderTel(buyer_tel)
                .postcode(buyer_postcode)
                .address(buyer_addr)
                .totalPrice(totalPrice)
                .shippingFee(shippingFee)
                .build();
    }
}
