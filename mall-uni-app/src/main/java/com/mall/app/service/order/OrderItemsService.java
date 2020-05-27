package com.mall.app.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemsEntity;

import java.util.List;

/**
 * 订单信息
 */
public interface OrderItemsService extends IService<OrderItemsEntity> {

}