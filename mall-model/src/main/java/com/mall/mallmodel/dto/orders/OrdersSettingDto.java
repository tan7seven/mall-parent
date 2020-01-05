package com.mall.mallmodel.dto.order;

import lombok.Data;

/**
 * 订单设置
 */
@Data
public class OrderSettingDto {

    /**
     * 订单ID
     */
    private Integer settingId;
    /**
     * 	正常订单超时时间(分)
     */
    private Integer normalOrderOvertime;

    /**
     * 	发货后自动确认收货时间（天）
     */
    private Integer confirmOvertime;
    /**
     * 	自动完成交易时间，不能申请售后（天）
     */
    private Integer finishOvertime;
    /**
     * 	订单完成后自动好评时间（天）
     */
    private Integer commentOvertime;
}
