package com.mall.manage.model.vo.product.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ProductTypeDetailVO implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String typeName;
    /**
     * 父类目编号
     */
    @ApiModelProperty(value = "父类编码")
    private Long parentId;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private Integer level;
    /**
     * 	排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 是否显示在导航栏
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    @ApiModelProperty(value = "状态")
    private Boolean usable;
}
