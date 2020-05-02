package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderService;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.mapper.order.OrderMapper;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

}
