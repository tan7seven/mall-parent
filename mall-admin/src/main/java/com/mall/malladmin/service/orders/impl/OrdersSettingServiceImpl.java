package com.mall.malladmin.service.orders.impl;

import com.mall.malladmin.dto.orders.OrdersSettingDto;
import com.mall.malladmin.entity.orders.OrdersSettingEntity;
import com.mall.malladmin.repository.orders.OrdersSettingRepository;
import com.mall.malladmin.service.orders.OrdersSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "ordersSettingService")
public class OrdersSettingServiceImpl implements OrdersSettingService {
    @Autowired
    private OrdersSettingRepository ordersSettingRepository;

    @Override
    public OrdersSettingEntity findOne() {
        List<OrdersSettingEntity> resultList = ordersSettingRepository.findAll();
        return resultList.isEmpty()?new OrdersSettingEntity():resultList.get(0);
    }

    @Override
    public OrdersSettingEntity update(Integer id, OrdersSettingDto dto) {
        OrdersSettingEntity entity = ordersSettingRepository.findById(id).get();
        BeanUtils.copyProperties(dto, entity);
        entity.setSettingId(id);
        return ordersSettingRepository.save(entity);
    }
}
