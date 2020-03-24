package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
public class ProductAttrNameDTO extends CommonDTO implements Serializable {
    /**
     * 编号
     */
    private Long id;
    /**
     * 商品类目ID
     */
    private Integer typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    private Boolean showed;
    /**
     * 	是否可用
     * 	0：是
     * 	1：否
     */
    private Boolean usable;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 类目名
     */
    private String typeName;
    /**
     * 输入方式
     */
    private Integer inputType;
    /**
     * 可输入值
     */
    private String inputData;
}
