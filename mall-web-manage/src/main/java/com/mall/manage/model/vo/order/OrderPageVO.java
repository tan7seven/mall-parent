package com.mall.manage.model.vo.order;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/31
 */
@Data
@ApiModel
public class OrderPageVO extends BaseVO {

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long id;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private Long orderCode;
    /**
     * 应付金额（实际支付金额）
     */
    @ApiModelProperty(value = "应付金额")
    private BigDecimal payPrice;
    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payType;

    @ApiModelProperty(value = "支付方式")
    private String payTypeName;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String orderStatusName;
    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
