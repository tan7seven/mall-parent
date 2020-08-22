package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum AdvertTypeEnum {
    HOME_CAROUSEL(1,"首页轮播-旋转木马"),
    HOME_TYPE(2,"首页分类TAB"),
    HOME_TYPE_PRODUCT(3,"首页分类加商品推荐"),
    HOME_GUESS_LIKE(4,"猜你喜欢"),
    ;
    private Integer code;
    private String desc;

    public static AdvertTypeEnum getByCode(Integer code){
        if (null == code) {
            log.error("AdvertTypeEnum getByCode error: code is null");
            return AdvertTypeEnum.HOME_CAROUSEL;
        }
        for (AdvertTypeEnum typeEnum : AdvertTypeEnum.values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        log.error("AdvertTypeEnum getByCode error: code is invalid");
        return AdvertTypeEnum.HOME_CAROUSEL;
    }
}
