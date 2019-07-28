package com.mall.malladmin.service.orders;

import com.mall.malladmin.dto.orders.OrdersSettingDto;
import com.mall.malladmin.entity.orders.OrdersSettingEntity;

/**
 * 订单设置
 */
public interface OrdersSettingService {

    /**
     * 获取配置信息
     * @return
     */
    OrdersSettingEntity findOne();

    /**
     * 修改
     * @param id
     * @param dto
     */
    OrdersSettingEntity update(Integer id, OrdersSettingDto dto);
}
