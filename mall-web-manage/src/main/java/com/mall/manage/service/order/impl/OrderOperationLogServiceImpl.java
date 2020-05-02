package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.mapper.order.OrderOperationLogMapper;
import com.mall.manage.service.order.OrderOperationLogService;
import org.springframework.stereotype.Service;


@Service(value = "orderOperationLogService")
public class OrderOperationLogServiceImpl extends ServiceImpl<OrderOperationLogMapper, OrderOperationLogEntity> implements OrderOperationLogService {

}
