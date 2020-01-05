package com.mall.common.enums;

import lombok.Getter;

/**
 * @description: 按钮名称、编码
 */
@Getter
public enum ButtonEnum {

    CREATE("CREATE","添加"),
    UPDATE("UPDATE","修改"),
    DELETE("DELETE","删除"),
    SWITCH("SWITCH","开关"),
    ;
    private String key;
    private String value;

    public static String getValue(String key) {
        for (ButtonEnum c : ButtonEnum.values()) {
            if (c.getKey().equals(key)) {
                return c.value;
            }
        }
        return null;
    }
    public static String getKey(String value) {
        for (ButtonEnum c : ButtonEnum.values()) {
            if (c.getValue() .equals(value)) {
                return c.key;
            }
        }
        return null;
    }
    ButtonEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }


}