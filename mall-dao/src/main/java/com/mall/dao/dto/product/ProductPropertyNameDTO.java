package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
public class ProductPropertyNameDTO extends CommonDTO implements Serializable {
    /**
     * 编号
     */
    private Integer propertyNameId;
    /**
     * 商品类目ID
     */
    private Integer typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 	商是否销售属性
     * 	0：否
     * 	1：是
     */
    private Integer isSale;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    private Integer isShow;
    /**
     * 	是否可用
     * 	0：是
     * 	1：否
     */
    private Integer isUsable;
    /**
     * 商品分类一级编号
     */
    private Integer parentId;

    /**
     *
     */
    private String typeName;
}
