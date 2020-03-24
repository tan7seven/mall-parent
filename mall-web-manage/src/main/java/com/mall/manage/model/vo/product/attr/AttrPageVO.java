package com.mall.manage.model.vo.product.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class AttrPageVO implements Serializable {

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;
    /**
     * 商品类目ID
     */
    @ApiModelProperty(value = "类目ID")
    private Long typeId;
    /**
     * 属性名
     */
    @ApiModelProperty(value = "属性名")
    private String name;
    /**
     * 	商是否销售属性
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
    /**
     * 	是否可用
     * 	0：是
     * 	1：否
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
    /**
     * 商品分类一级编号
     */
    @ApiModelProperty(value = "商品分类一级编号")
    private Long parentId;

    /**
     * 类目名
     */
    @ApiModelProperty(value = "类目名")
    private String typeName;
}
