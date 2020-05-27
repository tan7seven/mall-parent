package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderItemsService;
import com.mall.dao.entity.order.OrderItemsEntity;
import com.mall.dao.mapper.order.OrderItemsMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper,OrderItemsEntity> implements OrderItemsService {

}
