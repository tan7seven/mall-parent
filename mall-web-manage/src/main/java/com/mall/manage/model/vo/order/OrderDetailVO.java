package com.mall.manage.model.vo.order;

import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.enums.PayTypeEnum;
import com.mall.common.model.BaseModel;
import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/3
 */
@Data
@ApiModel
public class OrderDetailVO extends BaseVO {

    /**
     * 订单状态
     * @see com.mall.common.enums.OrderStatusEnum
     */
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    private String orderStatusName;

    @ApiModelProperty(value = "费用信息")
    private PriceMsg priceMsg;

    @ApiModelProperty(value = "配送信息")
    private DeliveryMsg deliveryMsg;

    @ApiModelProperty(value = "基础信息")
    private BaseMsg baseMsg;

    @ApiModelProperty(value = "时间信息")
    private TimeMsg timeMsg;

    @ApiModelProperty(value = "商品明细")
    private List<OrderItemVO> itemList;

    @ApiModelProperty(value = "操作列表")
    private List<OrderOperationLogVO> operationList;


    public String getOrderStatusName(){
        if (Objects.isNull(orderStatus)) {
            return OrderStatusEnum.UNPAID.getDesc();
        }
        return OrderStatusEnum.getByCode(this.orderStatus).getDesc();
    }

    @Data
    @ApiModel(value = "时间信息")
    public static class TimeMsg extends BaseModel {
        /**
         * 支付时间
         */
        @ApiModelProperty(value = "支付时间")
        private Date paymentTime;
        /**
         * 发货时间
         */
        @ApiModelProperty(value = "发货时间")
        private Date deliveryTime;
        /**
         * 确认收货时间
         */
        @ApiModelProperty(value = "确认收货时间")
        private Date receiveTime;
        /**
         * 新增时间
         */
        @ApiModelProperty(value = "新增时间")
        private Date createTime;
    }

    @Data
    @ApiModel(value = "基础信息")
    public static class BaseMsg extends BaseModel {
        /**
         * 订单ID
         */
        @ApiModelProperty(value = "订单ID")
        private Long orderCode;
        /**
         * 支付方式：0->未支付；1->支付宝；2->微信
         */
        @ApiModelProperty(value = "支付方式")
        private Integer payType;
        @ApiModelProperty(value = "支付方式")
        private String payTypeName;
        /**
         * 物流公司(配送方式)
         */
        @ApiModelProperty(value = "物流公司")
        private String deliveryCompany;
        /**
         * 物流单号
         */
        @ApiModelProperty(value = "物流单号")
        private String deliveryCode;
        /**
         * 登录账号
         */
        @ApiModelProperty(value = "登录账号")
        private String loginCode;
        /**
         * 订单备注
         */
        @ApiModelProperty(value = "订单备注")
        private String orderRemark;

        public String getPayTypeName(){
            if (Objects.isNull(payType)) {
                return PayTypeEnum.UN_PAY.getDesc();
            }
            return PayTypeEnum.getByCode(this.payType).getDesc();
        }

    }

    @Data
    @ApiModel(value = "配送信息")
    public static class DeliveryMsg extends BaseModel {
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
         * 省份/直辖市
         */
        @ApiModelProperty(value = "省份/直辖市")
        private String receiverProvince;
        /**
         * 城市
         */
        @ApiModelProperty(value = "城市")
        private String receiverCity;
        /**
         * 区
         */
        @ApiModelProperty(value = "区")
        private String receiverRegion;
        /**
         * 详细地址
         */
        @ApiModelProperty(value = "详细地址")
        private String receiverDetailAddress;
    }


    @Data
    @ApiModel(value = "费用信息")
    public static class PriceMsg extends BaseModel {
        /**
         * 订单总价格
         */
        @ApiModelProperty(value = "订单总价格")
        private BigDecimal totalPrice;
        /**
         * 应付金额（实际支付金额）
         */
        @ApiModelProperty(value = "应付金额（实际支付金额）")
        private BigDecimal payPrice;
        /**
         * 运费金额
         */
        @ApiModelProperty(value = "运费金额")
        private BigDecimal freightPrice;
        /**
         * 促销优化金额（促销价、满减、阶梯价），指应扣除金额
         */
        @ApiModelProperty(value = "促销优化金额（促销价、满减、阶梯价），指应扣除金额")
        private BigDecimal promotionPrice;

        /**
         * 优惠券抵扣金额
         */
        @ApiModelProperty(value = "优惠券抵扣金额")
        private BigDecimal couponPrice;
        /**
         * 管理员后台调整订单使用的折扣金额
         */
        @ApiModelProperty(value = "管理员后台调整订单使用的折扣金额")
        private BigDecimal discountPrice;
    }
}
