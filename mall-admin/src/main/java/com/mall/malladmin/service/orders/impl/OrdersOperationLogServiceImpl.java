package com.mall.malladmin.service.orders.impl;

import com.mall.malladmin.entity.orders.OrdersOperationLogEntity;
import com.mall.malladmin.repository.orders.OrdersOperationLogRepository;
import com.mall.malladmin.service.orders.OrdersOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "ordersOperationLogService")
public class OrdersOperationLogServiceImpl implements OrdersOperationLogService {

    @Autowired
    private OrdersOperationLogRepository ordersOperationLogRepository;

    @Override
    public OrdersOperationLogEntity save(OrdersOperationLogEntity entity) {
        return ordersOperationLogRepository.save(entity);
    }
}
