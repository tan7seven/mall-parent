package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEventEnum {
    CREATE("创建"),
    PAY("支付"),
    UNPAID_USER_CANCEL("未支付用户取消"),
    UNPAID_SYS_CANCEL("未支付系统自动取消"),
    DELIVER("发货"),
    RECEIVE("收货"),
    COMMENT("评价"),
    AFTER_APPLY("售后申请"),
    AFTER_APPLY_SUCCESS("售后申请通过"),
    AFTER_APPLY_FAILED("售后申请失败"),
    AFTER_APPLY_DONE("售后完成"),
    ;

    private String desc;
}
