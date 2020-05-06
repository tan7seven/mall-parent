package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ProductAttrValueVO extends BaseModel {
    @ApiModelProperty(value = "属性名编码")
    private Long nameId;
    @ApiModelProperty(value = "sku属性值")
    private String skuValue;
    @ApiModelProperty(value = "sku属性名")
    private String skuName;
}
