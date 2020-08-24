package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderItemsService;
import com.mall.dao.entity.order.OrderItemEntity;
import com.mall.dao.mapper.order.OrderItemMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemsService {

}
