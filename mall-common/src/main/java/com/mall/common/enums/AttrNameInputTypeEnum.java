package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttrNameInputTypeEnum {

    HAND(1,"手写"),
    SINGLE(2,"单选"),
    MULTIPLE(3,"多选"),
    ;
    private Integer code;
    private String value;

    public static Boolean isString(Integer inputType){
        if (AttrNameInputTypeEnum.MULTIPLE.getCode().equals(inputType)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static Boolean isArray(Integer inputType){
        if (AttrNameInputTypeEnum.MULTIPLE.getCode().equals(inputType)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static String getValueByCode(Integer code){
        if (null ==code) {
            return "";
        }
        for (AttrNameInputTypeEnum typeEnum : AttrNameInputTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum.getValue();
            }
        }
        return "";
    }
}
