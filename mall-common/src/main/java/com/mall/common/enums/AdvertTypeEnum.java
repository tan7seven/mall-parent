package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum AdvertTypeEnum {
    home_carousel(1,"首页轮播"),
    home_type(2,"首页分类tab"),
    home_type_product(3,"首页分类加商品推荐"),
    home_guess_like(4,"猜你喜欢"),
    ;
    private Integer code;
    private String desc;

    public static AdvertTypeEnum getByCode(Integer code){
        if (null == code) {
            log.error("AdvertTypeEnum getByCode error: code is null");
            return AdvertTypeEnum.home_carousel;
        }
        for (AdvertTypeEnum typeEnum : AdvertTypeEnum.values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        log.error("AdvertTypeEnum getByCode error: code is invalid");
        return AdvertTypeEnum.home_carousel;
    }
}
