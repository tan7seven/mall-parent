package com.mall.manage.service.order;


import com.mall.dao.entity.order.OrderOperationLogEntity;

/**
 * 订单操作日志
 */
public interface OrderOperationLogService {
    /**
     * 报错日志
     * @param entity
     * @return
     */
    OrderOperationLogEntity save(OrderOperationLogEntity entity);
}
