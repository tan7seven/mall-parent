package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.dto.order.OrderSettingDTO;
import com.mall.dao.entity.order.OrderSettingEntity;
import com.mall.dao.mapper.order.OrderSettingMapper;
import com.mall.manage.service.order.OrderSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {

    @Override
    public OrderSettingEntity findOne() {
        List<OrderSettingEntity> resultList = this.list();
        return resultList.isEmpty()?new OrderSettingEntity():resultList.get(0);
    }

    @Override
    public Boolean update(Integer id, OrderSettingDTO dto) {
        OrderSettingEntity entity = this.getById(id);
        BeanUtils.copyProperties(dto, entity);
        entity.setSettingId(id);
        return this.save(entity);
    }
}
