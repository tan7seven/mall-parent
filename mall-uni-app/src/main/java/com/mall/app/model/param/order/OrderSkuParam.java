package com.mall.app.model.param.order;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
@Data
@ApiModel
public class OrderSkuParam extends BaseModel {
    @NotNull(message = "SKU不能为空")
    @ApiModelProperty(value = "sku id")
    private Long skuId;
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private Integer amount;
}
