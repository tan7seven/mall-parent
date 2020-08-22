package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
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

    /**
     * 异常订单
     */
    ERROR_PAY_PRICE(401, "订单金额跟实付金额不符");
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
            return null;
        }
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }

    /**
     * 待支付
     */
    public static List<Integer> waitPaid(){
        return Arrays.asList(OrderStatusEnum.UNPAID.code);
    }

    /**
     * 待发货
     * @return
     */
    public static List<Integer> waitDelivery(){
        return Arrays.asList(OrderStatusEnum.PAID_WAIT_DELIVER.code);
    }

    /**
     * 待收货
     */
    public static List<Integer> waiReceived(){
        return Arrays.asList(OrderStatusEnum.PAID_WAIT_DELIVER.code, OrderStatusEnum.PAID_DELIVERED.code);
    }

    /**
     * 待评价
     */
    public static List<Integer> waitComment(){
        return Arrays.asList(OrderStatusEnum.RECEIVED.code);
    }

    /**
     * 售后订单
     */
    public static List<Integer> after(){
        return Arrays.asList(OrderStatusEnum.AFTER_WAIT_DELIVER_APPLY.code,
                OrderStatusEnum.AFTER_DELIVERED_APPLY.code,
                OrderStatusEnum.AFTER_RECEIVED_APPLY.code,

                OrderStatusEnum.AFTER_WAIT_DELIVER_SUCCESS.code,
                OrderStatusEnum.AFTER_DELIVERED_SUCCESS.code,
                OrderStatusEnum.AFTER_RECEIVED_SUCCESS.code,

                OrderStatusEnum.AFTER_WAIT_DELIVER_FAILED.code,
                OrderStatusEnum.AFTER_DELIVERED_FAILED.code,
                OrderStatusEnum.AFTER_RECEIVED_FAILED.code,

                OrderStatusEnum.AFTER_WAIT_DELIVER_DONE.code,
                OrderStatusEnum.AFTER_DELIVERED_DONE.code,
                OrderStatusEnum.AFTER_RECEIVED_DONE.code);
    }

    public static List<Integer> getByStatus(Integer status){
        if (status == 0) {
            return null;
        }
        if (status == 1) {
            return waitPaid();
        }
        if (status == 2) {
            return waiReceived();
        }
        if (status == 3) {
            return waitComment();
        }
        if (status == 4) {
            return after();
        }
        return null;
    }
}
