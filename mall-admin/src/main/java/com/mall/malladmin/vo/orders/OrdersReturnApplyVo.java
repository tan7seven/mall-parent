package com.mall.malladmin.vo.orders;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请表
 */
@Data
public class OrdersReturnApplyVo {
    /**
     * 主键
     */
    private String applyId;
    /**
     * 退货编码
     */
    private String applyCode;
    /**
     * 订单id
     */
    private String ordersId;
    /**
     * 订单商品明细编号
     */
    private String ordersItemsId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 退款金额
     */
    private BigDecimal returnPrice;
    /**
     * 公司收货地址id
     */
    private String companyAddressId;
    /**
     *	退货原因
     */
    private String returnReason;
    /**
     * 图片，以，号隔开
     */
    private String returnPic;
    /**
     * 退货状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    private String returnStatus;
    /**
     * 数量
     */
    private Integer returnAmount;
    /**
     * 申请时间
     */
    private Date createTime;
    /**
     * 处理时间
     */
    private Date handleTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    private Date receiveTime;

}
