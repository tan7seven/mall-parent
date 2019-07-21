package com.mall.malladmin.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.*;
import com.mall.malladmin.mapper.product.ProductPropertyMapper;
import com.mall.malladmin.mapper.product.ProductSkuMapper;
import com.mall.malladmin.repository.product.*;
import com.mall.malladmin.service.product.ProductSkuService;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductSkuDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl implements ProductSkuService {
    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    @Override
    public CommonResultDto add(ProductSkuDto dto) {
        ProductEntity product = productRepository.findById(dto.getProductId()).get();
        if(product.getPriceMin().compareTo(dto.getPrice())== 1){
            return new CommonResultDto().validateFailed("销售价格不能小于商品最低价！");
        }
        ProductSkuEntity entity = new ProductSkuEntity();
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setCost(dto.getCost()==null?new BigDecimal(0):dto.getCost());
        entity.setPrice(dto.getPrice()==null?new BigDecimal(0):dto.getPrice());
        entity.setProductId(dto.getProductId());
        entity.setSellSum(0);
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
        entity.setProperties(properties.toString());
        productSkuRepository.save(entity);
        return new CommonResultDto().success();
    }

    @Override
    public CommonResultDto update(Integer id, ProductSkuDto dto) {
        dto.setCreateTime(new Date());
        dto.setModifyTime(new Date());
        dto.setCost(dto.getCost()==null?new BigDecimal(0):dto.getCost());
        dto.setPrice(dto.getPrice()==null?new BigDecimal(0):dto.getPrice());
        dto.setStock(dto.getStock()==null?0:dto.getStock());
        dto.setSellSum(dto.getSellSum()==null?0:dto.getSellSum());
        dto.setSkuId(id);
        ProductSkuEntity skuEntity = productSkuRepository.findById(id).get();
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
        skuEntity.setProperties(properties.toString());
        productSkuRepository.save(skuEntity);
        return new CommonResultDto().success();
    }

    @Override
    public ProductSkuDto findById(Integer id) {
        ProductSkuEntity entity = productSkuRepository.findById(id).get();
        ProductSkuDto result = new ProductSkuDto();
        BeanUtils.copyProperties(entity, result);
        //设置商品属性值
        String[] properties = entity.getProperties().split("&");
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
        ProductEntity productEntity = productRepository.findById(entity.getProductId()).get();
        result.setProductName(productEntity.getProductName());
        //获取商品可选属性值
        List<ProductPropertyValueEntity> propertyValues= productPropertyValueRepository.findByProductId(productEntity.getProductId());
        List<Integer> propertiesInt = propertyValues.stream()
                .filter(productPropertyValueEntity -> ProductPropertyValueEntity.IS_SALE.equals(productPropertyValueEntity.getIsSale()))
                .map(productPropertyValueEntity->productPropertyValueEntity.getPropertyNameId())
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
        ProductTypeEntity typeEntity = productTypeRepository.findById(productEntity.getTypeId()).get();
        result.setTypeName(typeEntity.getTypeName());
        return result;
    }

    @Override
    public void delete(ProductSkuEntity entity) {
        productSkuRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productSkuRepository.deleteById(id);
    }

    @Override
    public List<ProductSkuEntity> findList(ProductSkuEntity entity) {
        Example<ProductSkuEntity> example = Example.of(entity);
        List<ProductSkuEntity> result = productSkuRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductSkuEntity> findPage(ProductSkuEntity entity, Pageable page) {
        Example<ProductSkuEntity> example = Example.of(entity);
        Page<ProductSkuEntity> result = productSkuRepository.findAll(example, page);
        return result;
    }

    @Override
    public PageInfo<ProductSkuDto> findPage(ProductSkuDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<ProductSkuDto> skuVoList = productSkuMapper.getList(dto);
        //将property值转换成对应value值
        for (ProductSkuDto skuDto: skuVoList){
            this.makePropertyKeyToValue(skuDto);
        }
        PageInfo<ProductSkuDto> page = new PageInfo<>(skuVoList);
        return page;
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private void makePropertyKeyToValue(ProductSkuDto dto){
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
            dto.setProperties(propertySb.toString());
        }
    }
}
