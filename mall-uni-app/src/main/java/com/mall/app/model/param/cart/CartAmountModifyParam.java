package com.mall.app.model.param.cart;

import com.mall.dao.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/26
 */
@Data
@ApiModel
public class CartAmountModifyParam extends BaseParam {
    @NotNull(message = "skuID不能为空")
    @ApiModelProperty(value = "skuID")
    private Long skuId;
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "修改后数量")
    private Integer amount;
}
