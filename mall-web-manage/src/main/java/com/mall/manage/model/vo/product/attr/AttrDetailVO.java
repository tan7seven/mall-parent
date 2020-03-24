package com.mall.manage.model.vo.product.attr;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AttrDetailVO extends BaseVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "类目ID")
    private Long typeId;
    @ApiModelProperty(value = "属性名")
    private String name;
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
    @ApiModelProperty(value = "类型 1：销售属性 2非销售属性")
    private Integer type;
    @ApiModelProperty(value = "输入类型 1手写 2单选 2多选")
    private Integer inputType;
    @ApiModelProperty(value = "可输入数据")
    private String inputData;
    @ApiModelProperty(value = "上级ID")
    private Long parentId;
}
