package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.*;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.service.product.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {

    @Autowired
    private ProductAttrValueService productPropertyValueService;

    @Autowired
    private ProductAttrNameService productPropertyNameService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductAttrNameMapper productPropertyMapper;

    @Override
    public RestResult add(ProductSkuDTO dto) {
        ProductEntity product = productService.getById(dto.getProductId());
        if(product.getMinPrice().compareTo(dto.getPrice())== 1){
            return RestResult.validateFailed("销售价格不能小于商品最低价！");
        }
        ProductSkuEntity entity = new ProductSkuEntity();
        entity.setProductId(dto.getProductId());
        entity.setStock(dto.getStock()==null?0:dto.getStock());
        entity.setPicUrl(dto.getPicUrl());
        StringBuffer properties = new StringBuffer();
        if(StringUtils.isNotBlank(dto.getPropertyValueA())){
            properties.append("&");
            properties.append(dto.getPropertyValueA());
        }
        if(StringUtils.isNotBlank(dto.getPropertyValueB())){
            properties.append("&");
            properties.append(dto.getPropertyValueB());
        }
        if(StringUtils.isNotBlank(dto.getPropertyValueC())){
            properties.append("&");
            properties.append(dto.getPropertyValueC());
        }
        entity.setAttrJson(properties.toString());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setSellSum(0);
        entity.setDelete(Boolean.FALSE);
        this.save(entity);
        return RestResult.success();
    }

    @Override
    public RestResult update(Long id, ProductSkuDTO dto) {
        dto.setCreateTime(new Date());
        dto.setModifyTime(new Date());
        dto.setCost(dto.getCost()==null?new BigDecimal(0):dto.getCost());
        dto.setPrice(dto.getPrice()==null?new BigDecimal(0):dto.getPrice());
        dto.setStock(dto.getStock()==null?0:dto.getStock());
        dto.setSellSum(dto.getSellSum()==null?0:dto.getSellSum());
        dto.setId(id);
        ProductSkuEntity skuEntity = this.getById(id);
        BeanUtils.copyProperties(dto, skuEntity);
        StringBuffer properties = new StringBuffer();
        if(StringUtils.isNotBlank(dto.getPropertyValueA())){
            properties.append("&");
            properties.append(dto.getPropertyValueA());
        }
        if(StringUtils.isNotBlank(dto.getPropertyValueB())){
            properties.append("&");
            properties.append(dto.getPropertyValueB());
        }
        if(StringUtils.isNotBlank(dto.getPropertyValueC())){
            properties.append("&");
            properties.append(dto.getPropertyValueC());
        }
        skuEntity.setAttrJson(properties.toString());
        this.save(skuEntity);
        return RestResult.success();
    }

    @Override
    public ProductSkuDTO queryById(Long id) {
        ProductSkuEntity entity = this.getById(id);
        ProductSkuDTO result = new ProductSkuDTO();
        BeanUtils.copyProperties(entity, result);
        //设置商品属性值
        String[] properties = entity.getAttrJson().split("&");
        if (properties.length > 1 && StringUtils.isNotBlank(properties[1])) {
            result.setPropertyValueA(properties[1]);
        }
        if (properties.length > 2 && StringUtils.isNotBlank(properties[2])) {
            result.setPropertyValueB(properties[2]);
        }
        if (properties.length > 3 && StringUtils.isNotBlank(properties[3])) {
            result.setPropertyValueC(properties[3]);
        }
        //获取商品信息
        ProductEntity productEntity = productService.getById(entity.getProductId());
        result.setProductName(productEntity.getProductName());
        //获取商品可选属性值
        List<ProductAttrValueEntity> propertyValues= productPropertyValueService.findByProductId(productEntity.getId());
        List<Long> propertiesInt = propertyValues.stream()
                .map(productPropertyValueEntity->productPropertyValueEntity.getNameId())
                .distinct().collect(Collectors.toList());
       if(null != properties && !propertiesInt.isEmpty() && propertiesInt.size()>=1 ){
            result.setPropertyValueAOptions(productPropertyMapper.findByPropertyNameIdAndProductId(propertiesInt.get(0), entity.getProductId()));
        }
        if(null != properties && !propertiesInt.isEmpty() && propertiesInt.size()>=2 ){
            result.setPropertyValueBOptions(productPropertyMapper.findByPropertyNameIdAndProductId(propertiesInt.get(1), entity.getProductId()));
        }
        if(null != properties && !propertiesInt.isEmpty() && propertiesInt.size()>=3 ){
            result.setPropertyValueCOptions(productPropertyMapper.findByPropertyNameIdAndProductId(propertiesInt.get(2), entity.getProductId()));
        }
        //获取分类名称
        ProductTypeEntity typeEntity = productTypeService.getById(productEntity.getTypeId());
        result.setTypeName(typeEntity.getTypeName());
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        productSkuMapper.updateIsDeleteById(id);
    }

    @Override
    public List<ProductSkuEntity> findList(ProductSkuEntity entity) {
        List<ProductSkuEntity> result = this.list();
        return result;
    }

    @Override
    public Page<ProductSkuEntity> findPage(ProductSkuEntity entity, Page page) {
        Page<ProductSkuEntity> result = (Page<ProductSkuEntity>) this.page(page);
        return result;
    }

    @Override
    public Page<ProductSkuDTO> findPage(ProductSkuDTO dto) {
        List<ProductSkuDTO> skuVoList = productSkuMapper.getList(dto);
        //将property值转换成对应value值
        for (ProductSkuDTO skuDto: skuVoList){
            this.makePropertyKeyToValue(skuDto);
        }
        return new Page<ProductSkuDTO>();
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private void makePropertyKeyToValue(ProductSkuDTO dto){
        StringBuffer propertySb = new StringBuffer();
        List<ProductAttrNameEntity> nameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, dto.getTypeId())
                .eq(ProductAttrNameEntity::getDeleted, Boolean.FALSE));
        List<ProductAttrValueEntity> valueList = productPropertyValueService.findByProductId(dto.getProductId());
        if(StringUtils.isNotBlank(dto.getProperties())){
            String skuProperties = dto.getProperties();
            String[] properties = skuProperties.split("&");
            for (String property: properties) {
                if(StringUtils.isBlank(property)){
                    continue;
                }
                String[] propertyValues = property.split(":");
                //获取propertyName值
                for (ProductAttrNameEntity nameEntity: nameList) {
                    if(propertyValues[0].equals(String.valueOf(nameEntity.getId()))){
                        propertySb.append(nameEntity.getName());
                        propertySb.append("：");
                    }
                }
                //获取propertyValue值
                for (ProductAttrValueEntity valueEntity: valueList) {
                    if(propertyValues[1].equals(String.valueOf(valueEntity.getId()))){
                        propertySb.append(valueEntity.getValue());
                        propertySb.append("、");
                    }
                }
            }
            dto.setProperties(propertySb.toString());
        }
    }
}
