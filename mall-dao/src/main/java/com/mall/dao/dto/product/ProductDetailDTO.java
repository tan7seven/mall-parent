package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;

/**
 *  商品属性名属性值关联表
 */
@Data
public class ProductDetailDTO extends CommonDTO implements Serializable {
    /**
     * 编号
     */
    private Integer detailId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 详情信息
     */
    private String describe;
}
