package com.mall.manage.controller.order.util;

import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/13
 */
public class OrderOperationLogUtil {

    public static OrderOperationLogEntity buildOrderOperationLog(OrderEntity orderEntity, String remark, Long userId){
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setOperationPerson(userId);
        logEntity.setOrderId(orderEntity.getId());
        logEntity.setOrderStatus(orderEntity.getOrderStatus());
        logEntity.setRemark(remark);
        return logEntity;
    }
}
