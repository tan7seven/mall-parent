package com.mall.manage.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.constant.CommonConstant;
import com.mall.dao.dto.order.OrderReturnApplyDTO;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.order.OrderReturnApplyEntity;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.product.ProductPropertyValueEntity;
import com.mall.dao.mapper.order.OrderReturnApplyMapper;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.order.OrderReturnApplyService;
import com.mall.manage.service.product.ProductPropertyNameService;
import com.mall.manage.service.product.ProductPropertyValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Autowired
    private ProductPropertyValueService productPropertyValueService;

    @Autowired
    private ProductPropertyNameService productPropertyNameService;

    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;

    @Autowired
    private OrderReturnApplyService orderReturnApplyService;

    @Override
    public OrderReturnApplyDTO findById(String id) {
        OrderReturnApplyDTO result = orderReturnApplyMapper.findById(id);
        ProductSkuDTO productSkuDto = new ProductSkuDTO();
        productSkuDto.setTypeId(result.getTypeId());
        productSkuDto.setProductId(result.getProductId());
        productSkuDto.setProperties(result.getProductProperty());
        result.setProductPropertyLabel(this.makePropertyKeyToValue(productSkuDto));
        return result;
    }

    @Override
    public Page<OrderReturnApplyDTO> getPage(OrderReturnApplyDTO dto) {
        Page<OrderReturnApplyDTO> page = new Page(dto.getPageNum(), dto.getPageSize());
        List<OrderReturnApplyDTO> resultList = orderReturnApplyMapper.findList(page, dto);
        page.setRecords(resultList);
        return page;
    }

    @Override
    public void updateApplyStatus(OrderReturnApplyDTO dto, UserDetailsImpl userDetails) {
        OrderReturnApplyEntity entity = orderReturnApplyService.getById(dto.getApplyId());
        //确认退货
        if (OrderReturnApplyEntity.RETURN_STATUS_RETURNING.equals(dto.getReturnStatus())) {
            entity.setHandlePerson(userDetails.getUserId());
            entity.setHandleTime(new Date());
            entity.setHandleRemark(dto.getHandleRemark());
            entity.setRealReturnPrice(dto.getRealReturnPrice());
            entity.setCompanyAddressId(dto.getAddressId());
            entity.setReturnStatus(dto.getReturnStatus());
        //确认收货
        }else if(OrderReturnApplyEntity.RETURN_STATUS_FINISHED.equals(dto.getReturnStatus())){
            entity.setReceivePerson(userDetails.getUserId());
            entity.setReceiveTime(new Date());
            entity.setReceiveRemark(dto.getReceiveRemark());
            entity.setReturnStatus(dto.getReturnStatus());
        //拒绝
        }else if(OrderReturnApplyEntity.RETURN_STATUS_REFUSE.equals(dto.getReturnStatus())){
            entity.setHandlePerson(userDetails.getUserId());
            entity.setHandleTime(new Date());
            entity.setHandleRemark(dto.getHandleRemark());
            entity.setReturnStatus(dto.getReturnStatus());
        }
        orderReturnApplyService.save(entity);
    }

    @Override
    public void deleteApply(List<String> ids) {
        for (String id : ids) {
            OrderReturnApplyEntity entity = orderReturnApplyService.getById(id);
            entity.setIsDelete(CommonConstant.IS_DELETE);
            orderReturnApplyService.save(entity);
        }
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private String makePropertyKeyToValue(ProductSkuDTO dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductPropertyNameEntity> nameList = productPropertyNameService.list(Wrappers.<ProductPropertyNameEntity>lambdaQuery()
                .eq(ProductPropertyNameEntity::getTypeId, dto.getTypeId())
                .eq(ProductPropertyNameEntity::getIsDelete, CommonConstant.NOT_DELETE));
        List<ProductPropertyValueEntity> valueList = productPropertyValueService.findByProductId(dto.getProductId());
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
