package com.mall.common.enums;

import com.mall.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */
@Getter
@AllArgsConstructor
public enum  OrderStatusEnum {
    UNPAID(101, "待支付订单"),
    UNPAID_USER_CANCEL(102, "未支付用户取消"),
    UNPAID_SYS_CANCEL(103, "未支付系统自动取消"),

    PAID_WAIT_DELIVER(201, "已支付待发货"),
    PAID_DELIVERED(202, "已支付已发货"),
    RECEIVED(203, "已收货"),
    COMMENT(204, "已评价"),

    AFTER_WAIT_DELIVER_APPLY(301, "未发货售后申请单"),
    AFTER_DELIVERED_APPLY(302, "已发货售后申请单"),
    AFTER_RECEIVED_APPLY(303, "已收货售后申请单"),

    AFTER_WAIT_DELIVER_SUCCESS(311, "未发货售后申请通过"),
    AFTER_DELIVERED_SUCCESS(312, "已发货售后申请通过"),
    AFTER_RECEIVED_SUCCESS(313, "已收货售后申请通过"),


    AFTER_WAIT_DELIVER_FAILED(321, "未发货售后申请失败"),
    AFTER_DELIVERED_FAILED(322, "已发货售后申请失败"),
    AFTER_RECEIVED_FAILED(323, "已收货售后申请失败"),

    AFTER_WAIT_DELIVER_DONE(331, "未发货售后申请完成"),
    AFTER_DELIVERED_DONE(332, "已发货售后申请完成"),
    AFTER_RECEIVED_DONE(333, "已收货售后申请完成"),
    ;

    /**
     * 状态值
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    public static OrderStatusEnum getByCode(Integer code){
        if (null == code) {
            throw new BusinessException("OrderStatusEnum error : getByCode the code is null");
        }
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        throw new BusinessException("OrderStatusEnum error : getByCode the code is invalid");
    }
}
