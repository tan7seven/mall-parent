package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 订单设置
 */
@Data
@TableName("mall_order_setting")
public class OrderSettingEntity {

    /**
     * 订单ID
     */
    @Id
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
