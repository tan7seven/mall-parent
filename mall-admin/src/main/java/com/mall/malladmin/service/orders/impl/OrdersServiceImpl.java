package com.mall.malladmin.service.orders.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.dto.orders.OrdersItemsDto;
import com.mall.malladmin.dto.orders.OrdersOperationLogDto;
import com.mall.malladmin.entity.orders.OrdersEntity;
import com.mall.malladmin.entity.orders.OrdersOperationLogEntity;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.mapper.orders.OrdersMapper;
import com.mall.malladmin.mapper.orders.OrdersOperationLogMapper;
import com.mall.malladmin.repository.orders.OrdersRepository;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.orders.OrdersOperationLogService;
import com.mall.malladmin.service.orders.OrdersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService

{
    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersOperationLogMapper ordersOperationLogMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Resource(name = "ordersOperationLogService")
    private OrdersOperationLogService ordersOperationLogService;

    @Override
    public PageInfo<OrdersDto> getPage(OrdersDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OrdersDto> resultList = ordersMapper.findList(dto);
        PageInfo<OrdersDto> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public OrdersDto getOrdersById(String id) {
        OrdersDto ordersDto = ordersMapper.findOrdersAndItems(id);
        //将属性的key转成value
        ordersDto.getOrdersItems().forEach(a -> this.makePropertyKeyToValue(a));
        //获取订单操作日志
        OrdersOperationLogDto operationLogDto = new OrdersOperationLogDto();
        operationLogDto.setOrdersId(id);
        List<OrdersOperationLogDto> operationLogDtoList = ordersOperationLogMapper.getOperationLog(operationLogDto);
        ordersDto.setOrdersOperationLog(operationLogDtoList);
        return ordersDto;
    }

    @Override
    public void updateReceiverInfo(OrdersDto dto, UserDetailsImpl userDetails) {
        OrdersEntity entity = ordersRepository.findById(dto.getOrdersId()).get();
        entity.setReceiverCity(dto.getReceiverCity());
        entity.setReceiverDetailAddress(dto.getReceiverDetailAddress());
        entity.setReceiverName(dto.getReceiverName());
        entity.setReceiverPhone(dto.getReceiverPhone());
        entity.setReceiverPostCode(dto.getReceiverPostCode());
        entity.setReceiverProvince(dto.getReceiverProvince());
        entity.setReceiverRegion(dto.getReceiverRegion());
        ordersRepository.save(entity);
        OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrdersId(dto.getOrdersId());
        logEntity.setOrdersStatus(dto.getOrdersStatus());
        logEntity.setRemark("修改收货信息");
        ordersOperationLogService.save(logEntity);
    }

    @Override
    public void updateMoneyInfo(OrdersDto dto, UserDetailsImpl userDetails) {
        OrdersEntity entity = ordersRepository.findById(dto.getOrdersId()).get();
        entity.setDiscountPrice(dto.getDiscountPrice());
        entity.setPayPrice(entity.getTotalPrice().add(entity.getFreightPrice())
                .subtract(entity.getPromotionPrice()).subtract(entity.getScorePrice()).subtract(entity.getCouponPrice())
                .subtract(dto.getDiscountPrice()));
        ordersRepository.save(entity);
        OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrdersId(dto.getOrdersId());
        logEntity.setOrdersStatus(dto.getOrdersStatus());
        logEntity.setRemark("修改订单价格："+ dto.getDiscountPrice().toString());
        ordersOperationLogService.save(logEntity);
    }

    @Override
    public void updateRemarkInfo(OrdersDto dto, UserDetailsImpl userDetails) {
        OrdersEntity entity = ordersRepository.findById(dto.getOrdersId()).get();
        entity.setOrdersRemark(dto.getOrdersRemark());
        ordersRepository.save(entity);
        OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrdersId(dto.getOrdersId());
        logEntity.setOrdersStatus(dto.getOrdersStatus());
        logEntity.setRemark("修改订单备注："+ dto.getOrdersRemark());
        ordersOperationLogService.save(logEntity);
    }

    @Override
    public void closeOrders(OrdersDto dto, UserDetailsImpl userDetails) {
        OrdersEntity entity = ordersRepository.findById(dto.getOrdersId()).get();
        OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
        logEntity.setCreateTime(new Date());
        logEntity.setOperationPerson(userDetails.getUserId());
        logEntity.setOrdersId(dto.getOrdersId());
        logEntity.setOrdersStatus(entity.getOrdersStatus());
        logEntity.setRemark("关闭订单、备注信息："+ dto.getOrdersRemark());
        ordersOperationLogService.save(logEntity);
        entity.setOrdersStatus(OrdersEntity.ORDERS_STATUS_INVALID);
        entity.setOrdersRemark(dto.getOrdersRemark());
        ordersRepository.save(entity);

    }

    @Override
    public void closeOrdersList(String[] ids, String remark, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrdersEntity entity = ordersRepository.findById(id).get();
            OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrdersId(id);
            logEntity.setOrdersStatus(entity.getOrdersStatus());
            logEntity.setRemark("关闭订单、备注信息："+ remark);
            ordersOperationLogService.save(logEntity);
            entity.setOrdersStatus(OrdersEntity.ORDERS_STATUS_INVALID);
            entity.setOrdersRemark(remark);
            ordersRepository.save(entity);
        }
    }

    @Override
    public void deleteOrders(String[] ids, UserDetailsImpl userDetails) {
        for (String id : ids) {
            OrdersEntity result = ordersRepository.findById(id).get();
            result.setIsDelete(CommonConstant.IS_DELETE);
            ordersRepository.save(result);
            OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrdersId(result.getOrdersId());
            logEntity.setOrdersStatus(result.getOrdersStatus());
            logEntity.setRemark("删除订单信息。");
            ordersOperationLogService.save(logEntity);
        }
    }

    @Override
    public void deliveryOrders(List<OrdersDto> dtoList, UserDetailsImpl userDetails) {
        for (OrdersDto dto : dtoList) {
            if (StringUtils.isBlank(dto.getOrdersId())) {
                continue;
            }
            OrdersEntity entity = ordersRepository.findById(dto.getOrdersId()).get();
            entity.setDeliveryCode(dto.getDeliveryCode());
            entity.setDeliveryCompany(dto.getDeliveryCompany());
            entity.setDeliveryTime(new Date());
            entity.setOrdersStatus(OrdersEntity.ORDERS_STATUS_SEND);
            entity = ordersRepository.save(entity);
            OrdersOperationLogEntity logEntity = new OrdersOperationLogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setOperationPerson(userDetails.getUserId());
            logEntity.setOrdersId(entity.getOrdersId());
            logEntity.setOrdersStatus(entity.getOrdersStatus());
            logEntity.setRemark("订单发货：物流单号-》"+dto.getDeliveryCode()+"；物流公司-》"+dto.getDeliveryCompany());
            ordersOperationLogService.save(logEntity);
        }
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private void makePropertyKeyToValue(OrdersItemsDto dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeId(dto.getTypeId());
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
