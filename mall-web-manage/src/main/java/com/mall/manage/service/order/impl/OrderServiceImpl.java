package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.dto.order.OrderItemsDTO;
import com.mall.dao.dto.order.OrderOperationLogDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.order.OrderMapper;
import com.mall.dao.mapper.order.OrderOperationLogMapper;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.order.OrderOperationLogService;
import com.mall.manage.service.order.OrderService;
import com.mall.manage.service.product.ProductAttrValueService;
import com.mall.manage.service.product.ProductAttrNameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService{
    @Autowired
    private ProductAttrValueService productPropertyValueService;

    @Autowired
    private ProductAttrNameService productPropertyNameService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderOperationLogMapper orderOperationLogMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Resource(name = "orderOperationLogService")
    private OrderOperationLogService orderOperationLogService;

    @Override
    public Page<OrderDTO> getPage(OrderDTO dto) {
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        List<OrderDTO> resultList = orderMapper.findList(page, dto);
        page.setRecords(resultList);
        return page;
    }

    @Override
    public OrderDTO getOrderById(String id) {
        OrderDTO orderDto = orderMapper.findOrderAndItems(id);
        //将属性的key转成value
        orderDto.getOrderItems().forEach(a -> this.makePropertyKeyToValue(a));
        //获取订单操作日志
        OrderOperationLogDTO operationLogDto = new OrderOperationLogDTO();
        operationLogDto.setOrderId(id);
        List<OrderOperationLogDTO> operationLogDtoList = orderOperationLogMapper.getOperationLog(operationLogDto);
        orderDto.setOrderOperationLog(operationLogDtoList);
        return orderDto;
    }

    @Override
    public void updateReceiverInfo(OrderDTO dto, UserDetailsImpl userDetails) {
        OrderEntity entity = orderService.getById(dto.getOrderId());
        entity.setReceiverCity(dto.getReceiverCity());
        entity.setReceiverDetailAddress(dto.getReceiverDetailAddress());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverPhone(dto.getReceiverPhone());
        entity.setReceiverPostCode(dto.getReceiverPostCode());
        entity.setReceiverProvince(dto.getReceiverProvince());
        entity.setReceiverRegion(dto.getReceiverRegion());
        orderService.save(entity);
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
        OrderEntity entity = orderService.getById(dto.getOrderId());
        entity.setDiscountPrice(dto.getDiscountPrice());
        entity.setPayPrice(entity.getTotalPrice().add(entity.getFreightPrice())
                .subtract(entity.getPromotionPrice()).subtract(entity.getScorePrice()).subtract(entity.getCouponPrice())
                .subtract(dto.getDiscountPrice()));
        orderService.save(entity);
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
        OrderEntity entity = orderService.getById(dto.getOrderId());
        entity.setOrderRemark(dto.getOrderRemark());
        orderService.save(entity);
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
        OrderEntity entity = orderService.getById(dto.getOrderId());
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(entity.getOrderStatus());
        logEntity.setRemark("关闭订单、备注信息："+ dto.getOrderRemark());
        orderOperationLogService.save(logEntity);
        entity.setOrderStatus(OrderEntity.ORDER_STATUS_INVALID);
        entity.setOrderRemark(dto.getOrderRemark());
        orderService.save(entity);

    }

    @Override
    public void closeOrderList(List<String> ids, String remark, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity entity = orderService.getById(id);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderId(id);
            logEntity.setOrderStatus(entity.getOrderStatus());
            logEntity.setRemark("关闭订单、备注信息："+ remark);
            orderOperationLogService.save(logEntity);
            entity.setOrderStatus(OrderEntity.ORDER_STATUS_INVALID);
            entity.setOrderRemark(remark);
            orderService.save(entity);
        }
    }

    @Override
    public void deleteOrder(List<String> ids, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity result = orderService.getById(id);
            orderService.save(result);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderId(result.getOrderId());
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
            OrderEntity entity = orderService.getById(dto.getOrderId());
            entity.setDeliveryCode(dto.getDeliveryCode());
            entity.setDeliveryCompany(dto.getDeliveryCompany());
            entity.setDeliveryTime(new Date());
            entity.setOrderStatus(OrderEntity.ORDER_STATUS_SEND);
            orderService.save(entity);
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderId(entity.getOrderId());
            logEntity.setOrderStatus(entity.getOrderStatus());
            logEntity.setRemark("订单发货：物流单号-》"+dto.getDeliveryCode()+"；物流公司-》"+dto.getDeliveryCompany());
            orderOperationLogService.save(logEntity);
        }
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private void makePropertyKeyToValue(OrderItemsDTO dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductAttrNameEntity> nameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, dto.getTypeId()));
        if(StringUtils.isNotBlank(dto.getProductProperty())){
            String skuProperties = dto.getProductProperty();
            String[] properties = skuProperties.split("&");
            for (String property: properties) {
                if(StringUtils.isBlank(property)){
                    continue;
                }
            }
            dto.setProductProperty(propertySb.toString());
        }
    }
}