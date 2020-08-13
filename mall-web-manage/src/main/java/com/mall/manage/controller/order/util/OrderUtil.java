package com.mall.manage.controller.order.util;

import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.enums.PayTypeEnum;
import com.mall.dao.dto.order.OrderOperationLogDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemEntity;
import com.mall.manage.model.vo.order.OrderDetailVO;
import com.mall.manage.model.vo.order.OrderItemVO;
import com.mall.manage.model.vo.order.OrderOperationLogVO;
import com.mall.manage.model.vo.order.OrderPageVO;
import com.mall.manage.utils.AttrUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/31
 */
public class OrderUtil {

    /**
     * 分页信息
     */
    public static List<OrderPageVO> buildPageVO(List<OrderEntity> orderList) {
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
            vo.setReceiverPhone(orderEntity.getReceiverPhone());
            result.add(vo);
        }
        return result;
    }

    public static OrderDetailVO buildDetailVO(OrderEntity orderEntity, List<OrderItemEntity> itemList, List<OrderOperationLogDTO> logDTOList) {
        OrderDetailVO result = new OrderDetailVO();
        result.setOrderId(orderEntity.getId());
        result.setOrderStatus(orderEntity.getOrderStatus());
        /** 基础信息 */
        OrderDetailVO.BaseMsg baseMsg = new OrderDetailVO.BaseMsg();
        baseMsg.setDeliveryCode(orderEntity.getDeliveryCode());
        baseMsg.setDeliveryCompany(orderEntity.getDeliveryCompany());
        baseMsg.setOrderCode(orderEntity.getOrderCode());
        baseMsg.setOrderRemark(orderEntity.getOrderRemark());
        baseMsg.setPayType(orderEntity.getPayType());
        baseMsg.setUserId(orderEntity.getUserId());
        result.setBaseMsg(baseMsg);

        /** 时间信息 */
        OrderDetailVO.TimeMsg timeMsg = new OrderDetailVO.TimeMsg();
        timeMsg.setCreateTime(orderEntity.getCreateTime());
        timeMsg.setDeliveryTime(orderEntity.getDeliveryTime());
        timeMsg.setPaymentTime(orderEntity.getPaymentTime());
        timeMsg.setReceiveTime(orderEntity.getReceiveTime());
        result.setTimeMsg(timeMsg);

        /** 配送信息 */
        OrderDetailVO.DeliveryMsg deliveryMsg = new OrderDetailVO.DeliveryMsg();
        deliveryMsg.setReceiverCity(orderEntity.getReceiverCity());
        deliveryMsg.setReceiverDetailAddress(orderEntity.getReceiverDetailAddress());
        deliveryMsg.setReceiverName(orderEntity.getReceiverName());
        deliveryMsg.setReceiverPhone(orderEntity.getReceiverPhone());
        deliveryMsg.setReceiverProvince(orderEntity.getReceiverProvince());
        deliveryMsg.setReceiverRegion(orderEntity.getReceiverRegion());
        result.setDeliveryMsg(deliveryMsg);

        /** 费用信息 */
        OrderDetailVO.PriceMsg priceMsg = new OrderDetailVO.PriceMsg();
        priceMsg.setCouponPrice(orderEntity.getCouponPrice());
        priceMsg.setDiscountPrice(orderEntity.getDiscountPrice());
        priceMsg.setFreightPrice(orderEntity.getFreightPrice());
        priceMsg.setPayPrice(orderEntity.getPayPrice());
        priceMsg.setPromotionPrice(orderEntity.getPromotionPrice());
        priceMsg.setTotalPrice(orderEntity.getTotalPrice());
        result.setPriceMsg(priceMsg);

        /** 订单商品明细列表 */
        if (!CollectionUtils.isEmpty(itemList)) {
            List<OrderItemVO> itemVOList = Lists.newArrayList();
            for (OrderItemEntity itemEntity : itemList) {
                OrderItemVO vo = new OrderItemVO();
                vo.setPicUrl(CommonConstant.IMG_PRE + itemEntity.getPicUrl());
                vo.setProductAmount(itemEntity.getSkuAmount());
                vo.setProductPrice(itemEntity.getSkuPrice());
                vo.setRealPrice(itemEntity.getRealPrice());
                vo.setProductName(itemEntity.getProductName());
                vo.setProductProperty(AttrUtil.getAttrStr(itemEntity.getSkuAttr()));
                itemVOList.add(vo);
            }
            result.setItemList(itemVOList);
        }

        /** 操作日志列表*/
        if (!CollectionUtils.isEmpty(logDTOList)) {
            List<OrderOperationLogVO> logVOList = Lists.newArrayList();
            for (OrderOperationLogDTO logDTO : logDTOList) {
                OrderOperationLogVO logVO = new OrderOperationLogVO();
                logVO.setCreateTime(logDTO.getCreateTime());
                logVO.setName(logDTO.getName());
                logVO.setOrderStatus(logDTO.getOrderStatus());
                logVO.setRemark(logDTO.getRemark());
                logVOList.add(logVO);
            }
            result.setOperationList(logVOList);
        }

        return result;
    }
}
