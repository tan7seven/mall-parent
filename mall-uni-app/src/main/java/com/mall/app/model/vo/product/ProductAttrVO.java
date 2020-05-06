package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class ProductAttrVO extends BaseModel {
    @ApiModelProperty(value = "属性名ID")
    private Long nameId;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "属性值")
    private List<ProductAttrValueVO> attrValueList;

    @ApiModelProperty(value = "类型 1：销售属性 2：显示参数")
    private Integer type;

    @ApiModelProperty(value = "显示参数值")
    private String showValue;
}
