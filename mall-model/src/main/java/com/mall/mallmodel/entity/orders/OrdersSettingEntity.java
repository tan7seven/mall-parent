package com.mall.mallmodel.entity.orders;

import lombok.Data;

import javax.persistence.*;

/**
 * 订单设置
 */
@Data
@Entity
@Table(name = "mall_orders_setting")
public class OrdersSettingEntity {

    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 32,name = "setting_Id")
    private Integer settingId;
    /**
     * 	正常订单超时时间(分)
     */
    @Column(name = "normal_orders_overtime")
    private Integer normalOrdersOvertime;

    /**
     * 	发货后自动确认收货时间（天）
     */
    @Column(name = "confirm_overtime")
    private Integer confirmOvertime;
    /**
     * 	自动完成交易时间，不能申请售后（天）
     */
    @Column(name = "finish_overtime")
    private Integer finishOvertime;
    /**
     * 	订单完成后自动好评时间（天）
     */
    @Column(name = "comment_overtime")
    private Integer commentOvertime;
}
