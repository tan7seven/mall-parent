package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.service.cart.CartService;
import com.mall.app.service.order.OrderService;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Autowired
    private CartService cartService;

    @Override
    public Boolean payDetail(OrderCreateParam param, Long userId) {
        /** 获取SKU列表*/
        List<CartEntity> cartList = cartService.list(Wrappers.<CartEntity>lambdaQuery()
                .in(CartEntity::getSkuId, param.getSkuIdList())
                .eq(CartEntity::getUserId, userId));
        // todo
        /** 获取营销列表（未做）*/
        // todo
        /** 获取用户信息 收货地址（未做）*/
        return null;
    }
}
