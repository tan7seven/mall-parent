package com.mall.malladmin.dto.orders;

import com.mall.malladmin.dto.common.CommonDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请表
 */
@Data
public class OrdersReturnApplyDto extends CommonDto{
    /**
     * 主键
     */
    private String applyId;
    /**
     * 分类id
     */
    private Integer typeId;
    /**
     * 商品图片
     */
    private String picUrl;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 退货编码
     */
    private String applyCode;
    /**
     * 商品属性
     */
    private String productProperty;
    /**
     * 商品属性
     */
    private String productPropertyLabel;
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
     * 登录账号
     */
    private String loginCode;
    /**
     * 退款金额
     */
    private BigDecimal returnPrice;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
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
    private Date receivePerson;
    /**
     * 确认收货备注
     */
    private Date receiveRemark;

}
