package com.mall.manage.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.order.OrderSettingDTO;
import com.mall.dao.entity.order.OrderSettingEntity;

/**
 * 订单设置
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    /**
     * 获取配置信息
     * @return
     */
    OrderSettingEntity findOne();

    /**
     * 修改
     * @param id
     * @param dto
     */
    Boolean update(Integer id, OrderSettingDTO dto);
}
