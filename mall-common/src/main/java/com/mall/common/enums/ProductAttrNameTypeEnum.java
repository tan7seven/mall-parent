package com.mall.common.enums;

import lombok.Getter;

@Getter
public enum  ProductAttrNameTypeEnum {

    SALE(1,"销售属性"),
    NOT_SALE(2,"显示参数"),
    ;
    private Integer code;
    private String name;

    public static String getName(Integer code) {
        for (ProductAttrNameTypeEnum c : ProductAttrNameTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }
    public static Integer getCode(String name) {
        for (ProductAttrNameTypeEnum c : ProductAttrNameTypeEnum.values()) {
            if (c.getName() .equals(name)) {
                return c.code;
            }
        }
        return null;
    }
    ProductAttrNameTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
