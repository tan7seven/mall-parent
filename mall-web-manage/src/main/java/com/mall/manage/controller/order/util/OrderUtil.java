package com.mall.manage.controller.order.util;

import com.google.common.collect.Lists;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.enums.PayTypeEnum;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.manage.model.vo.order.OrderPageVO;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/31
 */
public class OrderUtil {

    public static List<OrderPageVO> buildPageVO(List<OrderEntity> orderList){
        List<OrderPageVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(orderList)) {
            return result;
        }
        for (OrderEntity orderEntity : orderList) {
            OrderPageVO vo = new OrderPageVO();
            vo.setCreateTime(orderEntity.getCreateTime());
            vo.setId(orderEntity.getId());
            vo.setOrderStatus(orderEntity.getOrderStatus());
            vo.setOrderStatusName(OrderStatusEnum.getByCode(orderEntity.getOrderStatus()).getDesc());
            vo.setPayPrice(orderEntity.getPayPrice());
            vo.setOrderCode(orderEntity.getOrderCode());
            vo.setPayType(orderEntity.getPayType());
            vo.setPayTypeName(PayTypeEnum.getByCode(orderEntity.getPayType()).getDesc());
            vo.setReceiverName(orderEntity.getReceiverName());
            result.add(vo);
        }
        return result;
    }
}
