package com.mall.malladmin.service.orders;


import com.mall.malladmin.entity.orders.OrdersOperationLogEntity;

/**
 * 订单操作日志
 */
public interface OrdersOperationLogService {
    /**
     * 报错日志
     * @param entity
     * @return
     */
    OrdersOperationLogEntity save(OrdersOperationLogEntity entity);
}
