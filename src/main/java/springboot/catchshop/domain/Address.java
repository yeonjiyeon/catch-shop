package springboot.catchshop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String road; // 도로명 주소
    private String detail; // 상세 주소
    private String postalcode; // 우편 번호

    protected Address() {

    }

    public Address(String road, String detail, String postalcode) {
        this.road = road;
        this.detail = detail;
        this.postalcode = postalcode;
    }
}
