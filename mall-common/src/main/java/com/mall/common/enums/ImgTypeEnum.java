package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImgTypeEnum {
    PRODUCT("product","商品"),
    SKU("sku","SKU"),
    ;
    private String code;
    private String value;

}
