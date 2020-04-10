package com.mall.manage.model.param.product.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
@Data
@ApiModel
public class SkuAttrParam {
    @NotNull(message = "属性名ID不能为空")
    @ApiModelProperty(value = "属性名ID")
    private Long nameId;
    @NotBlank(message = "属性值不能为空")
    @ApiModelProperty(value = "属性值")
    private String skuValue;
    @NotBlank(message = "属性名不能为空")
    @ApiModelProperty(value = "属性名")
    private String skuName;
}
