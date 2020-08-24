package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleEnum {
        普通用户(0, "ROLE_USER"),
        管理员(1, "ROLE_ADMIN"),
        ;
        private Integer key;
        private String value;

        public static String getValue(Integer key) {
            for (AdminRoleEnum c : AdminRoleEnum.values()) {
                if (c.getKey().equals(key)) {
                    return c.value;
                }
            }
            return null;
        }
        public static Integer getKey(String value) {
            for (AdminRoleEnum c : AdminRoleEnum.values()) {
                if (c.getValue() .equals(value)) {
                    return c.key;
                }
            }
            return null;
        }
}
