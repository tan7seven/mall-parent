package com.mall.app.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.dao.entity.order.OrderEntity;

/**
 * 订单信息
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 创建订单
     * @return
     */
    Boolean payDetail(OrderCreateParam param, Long userId);
}
