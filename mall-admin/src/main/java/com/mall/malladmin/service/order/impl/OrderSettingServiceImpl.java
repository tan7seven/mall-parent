package com.mall.malladmin.service.order.impl;

import com.mall.malladmin.dto.order.OrderSettingDTO;
import com.mall.malladmin.entity.order.OrderSettingEntity;
import com.mall.malladmin.repository.order.OrderSettingRepository;
import com.mall.malladmin.service.order.OrderSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "orderSettingService")
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingRepository orderSettingRepository;

    @Override
    public OrderSettingEntity findOne() {
        List<OrderSettingEntity> resultList = orderSettingRepository.findAll();
        return resultList.isEmpty()?new OrderSettingEntity():resultList.get(0);
    }

    @Override
    public OrderSettingEntity update(Integer id, OrderSettingDTO dto) {
        OrderSettingEntity entity = orderSettingRepository.findById(id).get();
        BeanUtils.copyProperties(dto, entity);
        entity.setSettingId(id);
        return orderSettingRepository.save(entity);
    }
}
