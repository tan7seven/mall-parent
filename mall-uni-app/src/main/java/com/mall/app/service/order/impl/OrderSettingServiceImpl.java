package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderSettingService;
import com.mall.dao.entity.order.OrderSettingEntity;
import com.mall.dao.mapper.order.OrderSettingMapper;
import org.springframework.stereotype.Service;


@Service(value = "orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {

}
