package com.mall.malladmin.service.order.impl;

import com.mall.malladmin.entity.order.OrderOperationLogEntity;
import com.mall.malladmin.repository.order.OrderOperationLogRepository;
import com.mall.malladmin.service.order.OrderOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "orderOperationLogService")
public class OrderOperationLogServiceImpl implements OrderOperationLogService {

    @Autowired
    private OrderOperationLogRepository orderOperationLogRepository;

    @Override
    public OrderOperationLogEntity save(OrderOperationLogEntity entity) {
        return orderOperationLogRepository.save(entity);
    }
}
