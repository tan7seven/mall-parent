package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrValueMapper;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductAttrValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "productPropertyValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttrValueService {

    @Resource(name = "productAttrNameService")
    private ProductAttrNameService productAttrNameService;

    @Override
    public List<AttrValueVO> getByProductId(Long productId) {
        List<ProductAttrValueEntity> entityList =  this.list(Wrappers.<ProductAttrValueEntity>lambdaQuery()
                .eq(ProductAttrValueEntity::getProductId, productId)
                .eq(ProductAttrValueEntity::getType, ProductAttrNameTypeEnum.SALE.getCode()));
        List<Long> nameIdList = entityList.stream().map(s -> s.getNameId()).collect(Collectors.toList());
        List<AttrValueVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(nameIdList)) {
            return result;
        }
        List<ProductAttrNameEntity> nameList = productAttrNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .in(ProductAttrNameEntity::getId, nameIdList));
        for (ProductAttrValueEntity entity : entityList) {
            AttrValueVO vo = new AttrValueVO();
            BeanUtils.copyProperties(entity, vo);
            for (ProductAttrNameEntity nameEntity : nameList) {
                if (nameEntity.getId().equals(entity.getNameId())) {
                    vo.setSkuName(nameEntity.getName());
                }
            }
            result.add(vo);
        }
        return result;
    }
}
