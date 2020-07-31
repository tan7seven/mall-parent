package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.dto.order.OrderOperationLogDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.mapper.order.OrderMapper;
import com.mall.dao.mapper.order.OrderOperationLogMapper;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.order.OrderOperationLogService;
import com.mall.manage.service.order.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService{


    @Autowired
    private OrderOperationLogMapper orderOperationLogMapper;

    @Resource(name = "orderOperationLogService")
    private OrderOperationLogService orderOperationLogService;

    @Override
    public OrderDTO getOrderById(Long id) {
        List<OrderOperationLogDTO> operationLogDtoList = orderOperationLogMapper.getOperationLog(null);

        return null;
    }

    @Override
    public void updateReceiverInfo(OrderDTO dto, UserDetailsImpl userDetails) {
        OrderEntity entity = this.getById(dto.getOrderId());
        entity.setReceiverCity(dto.getReceiverCity());
        entity.setReceiverDetailAddress(dto.getReceiverDetailAddress());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverPhone(dto.getReceiverPhone());
        entity.setReceiverProvince(dto.getReceiverProvince());
        entity.setReceiverRegion(dto.getReceiverRegion());
        this.save(entity);
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(dto.getOrderStatus());
        logEntity.setRemark("修改收货信息");
        orderOperationLogService.save(logEntity);
    }

    @Override
    public void updateMoneyInfo(OrderDTO dto, UserDetailsImpl userDetails) {
        OrderEntity entity = this.getById(dto.getOrderId());
        entity.setDiscountPrice(dto.getDiscountPrice());
        entity.setPayPrice(entity.getTotalPrice().add(entity.getFreightPrice())
                .subtract(dto.getDiscountPrice()));
        this.save(entity);
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(dto.getOrderStatus());
        logEntity.setRemark("修改订单价格："+ dto.getDiscountPrice().toString());
        orderOperationLogService.save(logEntity);
    }

    @Override
    public void updateRemarkInfo(OrderDTO dto, UserDetailsImpl userDetails) {
        OrderEntity entity = this.getById(dto.getOrderId());
        entity.setOrderRemark(dto.getOrderRemark());
        this.save(entity);
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(dto.getOrderStatus());
        logEntity.setRemark("修改订单备注："+ dto.getOrderRemark());
        orderOperationLogService.save(logEntity);
    }

    @Override
    public void closeOrder(OrderDTO dto, UserDetailsImpl userDetails) {
        OrderEntity entity = this.getById(dto.getOrderId());
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(entity.getOrderStatus());
        logEntity.setRemark("关闭订单、备注信息："+ dto.getOrderRemark());
        orderOperationLogService.save(logEntity);
        entity.setOrderRemark(dto.getOrderRemark());
        this.save(entity);

    }

    @Override
    public void closeOrderList(List<String> ids, String remark, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity entity = this.getById(id);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderId(id);
            logEntity.setOrderStatus(entity.getOrderStatus());
            logEntity.setRemark("关闭订单、备注信息："+ remark);
            orderOperationLogService.save(logEntity);
            entity.setOrderRemark(remark);
            this.save(entity);
        }
    }

    @Override
    public void deleteOrder(List<String> ids, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity result = this.getById(id);
            this.save(result);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderStatus(result.getOrderStatus());
            logEntity.setRemark("删除订单信息。");
            orderOperationLogService.save(logEntity);
        }
    }

    @Override
    public void deliveryOrder(List<OrderDTO> dtoList, UserDetailsImpl userDetails) {
        for (OrderDTO dto : dtoList) {
            if (StringUtils.isBlank(dto.getOrderId())) {
                continue;
            }
            OrderEntity entity = this.getById(dto.getOrderId());
            entity.setDeliveryCode(dto.getDeliveryCode());
            entity.setDeliveryCompany(dto.getDeliveryCompany());
            entity.setDeliveryTime(new Date());
            this.save(entity);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderStatus(entity.getOrderStatus());
            logEntity.setRemark("订单发货：物流单号-》"+dto.getDeliveryCode()+"；物流公司-》"+dto.getDeliveryCompany());
            orderOperationLogService.save(logEntity);
        }
    }

}
