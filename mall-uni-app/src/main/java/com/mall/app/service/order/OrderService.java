package com.mall.app.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.param.order.BuildPayDetailParam;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.dao.entity.order.OrderEntity;

import java.util.List;

/**
 * 订单信息
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 支付详情
     * @return
     */
    PayDetailVO payDetail(BuildPayDetailParam param, Long userId);

    /**
     * 创建订单
     * @return
     */
    CreateOrderVO createOrder(OrderCreateParam param, Long userId);
}