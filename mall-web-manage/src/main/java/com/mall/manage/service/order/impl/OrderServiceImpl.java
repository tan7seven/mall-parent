package com.mall.manage.service.order.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.constant.CommonConstant;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.dto.order.OrderItemsDTO;
import com.mall.dao.dto.order.OrderOperationLogDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.product.ProductPropertyValueEntity;
import com.mall.dao.mapper.order.OrderMapper;
import com.mall.dao.mapper.order.OrderOperationLogMapper;
import com.mall.dao.repository.order.OrderRepository;
import com.mall.dao.repository.product.ProductPropertyNameRepository;
import com.mall.dao.repository.product.ProductPropertyValueRepository;
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
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderOperationLogMapper orderOperationLogMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Resource(name = "orderOperationLogService")
    private OrderOperationLogService orderOperationLogService;

    @Override
    public PageInfo<OrderDTO> getPage(OrderDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OrderDTO> resultList = orderMapper.findList(dto);
        PageInfo<OrderDTO> page = new PageInfo<>(resultList);
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
        OrderEntity entity = orderRepository.findById(dto.getOrderId()).get();
        entity.setReceiverCity(dto.getReceiverCity());
        entity.setReceiverDetailAddress(dto.getReceiverDetailAddress());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverPhone(dto.getReceiverPhone());
        entity.setReceiverPostCode(dto.getReceiverPostCode());
        entity.setReceiverProvince(dto.getReceiverProvince());
        entity.setReceiverRegion(dto.getReceiverRegion());
        orderRepository.save(entity);
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
        OrderEntity entity = orderRepository.findById(dto.getOrderId()).get();
        entity.setDiscountPrice(dto.getDiscountPrice());
        entity.setPayPrice(entity.getTotalPrice().add(entity.getFreightPrice())
                .subtract(entity.getPromotionPrice()).subtract(entity.getScorePrice()).subtract(entity.getCouponPrice())
                .subtract(dto.getDiscountPrice()));
        orderRepository.save(entity);
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
        OrderEntity entity = orderRepository.findById(dto.getOrderId()).get();
        entity.setOrderRemark(dto.getOrderRemark());
        orderRepository.save(entity);
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
        OrderEntity entity = orderRepository.findById(dto.getOrderId()).get();
        OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrderId(dto.getOrderId());
        logEntity.setOrderStatus(entity.getOrderStatus());
        logEntity.setRemark("关闭订单、备注信息："+ dto.getOrderRemark());
        orderOperationLogService.save(logEntity);
        entity.setOrderStatus(OrderEntity.ORDER_STATUS_INVALID);
        entity.setOrderRemark(dto.getOrderRemark());
        orderRepository.save(entity);

    }

    @Override
    public void closeOrderList(List<String> ids, String remark, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity entity = orderRepository.findById(id).get();
            OrderOperationLogEntity logEntity = new OrderOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrderId(id);
            logEntity.setOrderStatus(entity.getOrderStatus());
            logEntity.setRemark("关闭订单、备注信息："+ remark);
            orderOperationLogService.save(logEntity);
            entity.setOrderStatus(OrderEntity.ORDER_STATUS_INVALID);
            entity.setOrderRemark(remark);
            orderRepository.save(entity);
        }
    }

    @Override
    public void deleteOrder(List<String> ids, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrderEntity result = orderRepository.findById(id).get();
            result.setIsDelete(CommonConstant.IS_DELETE);
            orderRepository.save(result);
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
            OrderEntity entity = orderRepository.findById(dto.getOrderId()).get();
            entity.setDeliveryCode(dto.getDeliveryCode());
            entity.setDeliveryCompany(dto.getDeliveryCompany());
            entity.setDeliveryTime(new Date());
            entity.setOrderStatus(OrderEntity.ORDER_STATUS_SEND);
            entity = orderRepository.save(entity);
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
        List<ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeIdAndIsDelete(dto.getTypeId(), CommonConstant.NOT_DELETE);
        List<ProductPropertyValueEntity> valueList = productPropertyValueRepository.findByProductId(dto.getProductId());
        if(StringUtils.isNotBlank(dto.getProductProperty())){
            String skuProperties = dto.getProductProperty();
            String[] properties = skuProperties.split("&");
            for (String property: properties) {
                if(StringUtils.isBlank(property)){
                    continue;
                }
                String[] propertyValues = property.split(":");
                //获取propertyName值
                for (ProductPropertyNameEntity nameEntity: nameList) {
                    if(propertyValues[0].equals(String.valueOf(nameEntity.getPropertyNameId()))){
                        propertySb.append(nameEntity.getName());
                        propertySb.append("：");
                    }
                }
                //获取propertyValue值
                for (ProductPropertyValueEntity valueEntity: valueList) {
                    if(propertyValues[1].equals(String.valueOf(valueEntity.getPropertyValueId()))){
                        propertySb.append(valueEntity.getValue());
                        propertySb.append("、");
                    }
                }
            }
            dto.setProductProperty(propertySb.toString());
        }
    }
}
