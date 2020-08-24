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
public class OrderUpdateReceiverInfoParam extends BaseParam {

    @NotNull(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private Long orderId;

    @NotBlank(message = "收货人姓名不能为空")
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    @NotBlank(message = "收货人电话不能为空")
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;

    @NotBlank(message = "收货地址不能为空")
    @ApiModelProperty(value = "收货地址")
    private String receiverDetailAddress;

    @NotBlank(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String receiverProvince;

    @NotBlank(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String receiverCity;

    @NotBlank(message = "区不能为空")
    @ApiModelProperty(value = "区")
    private String receiverRegion;
}
