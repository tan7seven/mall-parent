package com.mall.malladmin.service.orders.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import com.mall.malladmin.dto.product.ProductSkuDto;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.mapper.orders.OrdersReturnApplyMapper;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.service.orders.OrdersReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ordersReturnApplyService")
public class OrdersReturnApplyServiceImpl implements OrdersReturnApplyService {

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private OrdersReturnApplyMapper ordersReturnApplyMapper;

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
