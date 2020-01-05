package com.mall.malladmin.service.order.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.order.OrderReturnApplyDTO;
import com.mall.malladmin.dto.product.ProductSkuDTO;
import com.mall.malladmin.entity.order.OrderReturnApplyEntity;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.mapper.order.OrderReturnApplyMapper;
import com.mall.malladmin.repository.order.OrderReturnApplyRepository;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.order.OrderReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "orderReturnApplyService")
public class OrderReturnApplyServiceImpl implements OrderReturnApplyService {

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;

    @Autowired
    private OrderReturnApplyRepository orderReturnApplyRepository;

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
    public PageInfo<OrderReturnApplyDTO> getPage(OrderReturnApplyDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OrderReturnApplyDTO> resultList = orderReturnApplyMapper.findList(dto);
        PageInfo<OrderReturnApplyDTO> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public void updateApplyStatus(OrderReturnApplyDTO dto, UserDetailsImpl userDetails) {
        OrderReturnApplyEntity entity = orderReturnApplyRepository.findById(dto.getApplyId()).get();
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
        orderReturnApplyRepository.save(entity);
    }

    @Override
    public void deleteApply(List<String> ids) {
        for (String id : ids) {
            OrderReturnApplyEntity entity = orderReturnApplyRepository.findById(id).get();
            entity.setIsDelete(CommonConstant.IS_DELETE);
            orderReturnApplyRepository.save(entity);
        }
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private String makePropertyKeyToValue(ProductSkuDTO dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeIdAndIsDelete(dto.getTypeId(), CommonConstant.NOT_DELETE);
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
