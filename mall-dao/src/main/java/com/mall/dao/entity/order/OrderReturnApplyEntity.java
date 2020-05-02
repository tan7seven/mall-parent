package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请表
 */
@Data
@TableName("mall_order_return_apply")
public class OrderReturnApplyEntity extends BaseEntity {
    /**
     * 退货状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    public static final String RETURN_STATUS_WAIT = "0";
    public static final String RETURN_STATUS_RETURNING = "1";
    public static final String RETURN_STATUS_FINISHED = "2";
    public static final String RETURN_STATUS_REFUSE="3";

    /**
     * 主键
     */
    private String applyId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单商品明细编号
     */
    private String orderItemsId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 申请退款金额
     */
    private BigDecimal returnPrice;
    /**
     * 实际退款金额
     */
    private BigDecimal realReturnPrice;
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
     * 处理备注
     */
    private String handleRemark;
    /**
     * 处理人
     */
    private String handlePerson;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    private Date receiveTime;
    /**
     * 确认收货人
     */
    private String receivePerson;
    /**
     * 确认收货备注
     */
    private String receiveRemark;
    /**
     * 是否删除
     * 0-》否
     * 1-》是
     */
    private Boolean isDelete;
}
