package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.order.OrderItemEntity;
import com.mall.dao.mapper.order.OrderItemMapper;
import com.mall.manage.service.order.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/7
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {
}
