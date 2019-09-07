package com.mall.malladmin.service.orders.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import com.mall.malladmin.dto.product.ProductSkuDto;
import com.mall.malladmin.entity.orders.OrdersReturnApplyEntity;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.mapper.orders.OrdersReturnApplyMapper;
import com.mall.malladmin.repository.orders.OrdersReturnApplyRepository;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.orders.OrdersReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "ordersReturnApplyService")
public class OrdersReturnApplyServiceImpl implements OrdersReturnApplyService {

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private OrdersReturnApplyMapper ordersReturnApplyMapper;

    @Autowired
    private OrdersReturnApplyRepository ordersReturnApplyRepository;

    @Override
    public OrdersReturnApplyDto findById(String id) {
        OrdersReturnApplyDto result = ordersReturnApplyMapper.findById(id);
        ProductSkuDto productSkuDto = new ProductSkuDto();
        productSkuDto.setTypeId(result.getTypeId());
        productSkuDto.setProductId(result.getProductId());
        productSkuDto.setProperties(result.getProductProperty());
        result.setProductPropertyLabel(this.makePropertyKeyToValue(productSkuDto));
        return result;
    }

    @Override
    public PageInfo<OrdersReturnApplyDto> getPage(OrdersReturnApplyDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OrdersReturnApplyDto> resultList = ordersReturnApplyMapper.findList(dto);
        PageInfo<OrdersReturnApplyDto> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public void updateApplyStatus(OrdersReturnApplyDto dto, UserDetailsImpl userDetails) {
        OrdersReturnApplyEntity entity = ordersReturnApplyRepository.findById(dto.getApplyId()).get();
        //确认退货
        if (OrdersReturnApplyEntity.RETURN_STATUS_RETURNING.equals(dto.getReturnStatus())) {
            entity.setHandlePerson(userDetails.getUserId());
            entity.setHandleTime(new Date());
            entity.setHandleRemark(dto.getHandleRemark());
            entity.setRealReturnPrice(dto.getRealReturnPrice());
            entity.setCompanyAddressId(dto.getAddressId());
            entity.setReturnStatus(dto.getReturnStatus());
        //确认收货
        }else if(OrdersReturnApplyEntity.RETURN_STATUS_FINISHED.equals(dto.getReturnStatus())){
            entity.setReceivePerson(userDetails.getUserId());
            entity.setReceiveTime(new Date());
            entity.setReceiveRemark(dto.getReceiveRemark());
            entity.setReturnStatus(dto.getReturnStatus());
        //拒绝
        }else if(OrdersReturnApplyEntity.RETURN_STATUS_REFUSE.equals(dto.getReturnStatus())){
            entity.setHandlePerson(userDetails.getUserId());
            entity.setHandleTime(new Date());
            entity.setHandleRemark(dto.getHandleRemark());
            entity.setReturnStatus(dto.getReturnStatus());
        }
        ordersReturnApplyRepository.save(entity);
    }

    @Override
    public void deleteApply(String[] ids) {
        for (String id : ids) {
            OrdersReturnApplyEntity entity = ordersReturnApplyRepository.findById(id).get();
            entity.setIsDelete(CommonConstant.IS_DELETE);
            ordersReturnApplyRepository.save(entity);
        }
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private String makePropertyKeyToValue(ProductSkuDto dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeId(dto.getTypeId());
        List<ProductPropertyValueEntity> valueList = productPropertyValueRepository.findByProductId(dto.getProductId());
        if(StringUtils.isNotBlank(dto.getProperties())){
            String skuProperties = dto.getProperties();
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
            return propertySb.toString();
        }
        return"";
    }
}
