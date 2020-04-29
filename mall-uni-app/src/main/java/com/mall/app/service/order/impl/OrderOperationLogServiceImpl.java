package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderOperationLogService;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.mapper.order.OrderOperationLogMapper;
import org.springframework.stereotype.Service;


@Service(value = "orderOperationLogService")
public class OrderOperationLogServiceImpl extends ServiceImpl<OrderOperationLogMapper, OrderOperationLogEntity> implements OrderOperationLogService {

}
