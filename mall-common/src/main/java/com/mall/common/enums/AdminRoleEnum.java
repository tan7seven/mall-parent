package com.mall.common.enums;

public enum AdminRoleEnum {
        普通用户("0", "ROLE_USER"),
        管理员("1", "ROLE_ADMIN"),
        ;
        private String key;
        private String value;

        public static String getValue(String key) {
            for (AdminRoleEnum c : AdminRoleEnum.values()) {
                if (c.getKey().equals(key)) {
                    return c.value;
                }
            }
            return null;
        }
        public static String getKey(String value) {
            for (AdminRoleEnum c : AdminRoleEnum.values()) {
                if (c.getValue() .equals(value)) {
                    return c.key;
                }
            }
            return null;
        }
        AdminRoleEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }
        public String getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }

}
