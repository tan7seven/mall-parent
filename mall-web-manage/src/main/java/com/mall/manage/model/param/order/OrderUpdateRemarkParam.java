package com.mall.manage.model.param.order;

import com.mall.common.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/6
 */
@Data
@ApiModel
public class OrderUpdateRemarkParam extends BaseParam {

    @NotBlank(message = "订单备注不能为空")
    @ApiModelProperty(value = "订单备注")
    private String remark;

    @NotNull(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private Long orderId;
}
