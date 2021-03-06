package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    UN_PAY(1, "未支付"),
    ALI(2, "支付宝"),
    WX(3, "微信"),
    ;

    private Integer code;
    private String desc;

    public static PayTypeEnum getByCode(Integer code){
        if (Objects.isNull(code)) {
            return null;
        }
        for (PayTypeEnum typeEnum : PayTypeEnum.values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}
