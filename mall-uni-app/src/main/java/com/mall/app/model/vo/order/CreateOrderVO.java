package com.mall.app.model.vo.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
@Data
@ApiModel
public class CreateOrderVO extends BaseModel {

    @ApiModelProperty(value = "支付单号")
    private Long orderCode;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payPrice;
}
