package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.constant.CommonConstant;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.product.ProductPropertyNameMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.dao.repository.product.*;
import com.mall.manage.service.product.ProductSkuService;
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
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {
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
    private ProductPropertyNameMapper productPropertyMapper;

    @Override
    public RestResult add(ProductSkuDTO dto) {
        com.mall.dao.entity.product.ProductEntity product = productRepository.findById(dto.getProductId()).get();
        if(product.getPriceMin().compareTo(dto.getPrice())== 1){
            return RestResult.validateFailed("销售价格不能小于商品最低价！");
        }
        com.mall.dao.entity.product.ProductSkuEntity entity = new com.mall.dao.entity.product.ProductSkuEntity();
        entity.setCost(dto.getCost()==null?new BigDecimal(0):dto.getCost());
        entity.setPrice(dto.getPrice()==null?new BigDecimal(0):dto.getPrice());
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
        entity.setProperties(properties.toString());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setSellSum(0);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        productSkuRepository.save(entity);
        return RestResult.success();
    }

    @Override
    public RestResult update(Integer id, ProductSkuDTO dto) {
        dto.setCreateTime(new Date());
        dto.setModifyTime(new Date());
        dto.setCost(dto.getCost()==null?new BigDecimal(0):dto.getCost());
        dto.setPrice(dto.getPrice()==null?new BigDecimal(0):dto.getPrice());
        dto.setStock(dto.getStock()==null?0:dto.getStock());
        dto.setSellSum(dto.getSellSum()==null?0:dto.getSellSum());
        dto.setSkuId(id);
        com.mall.dao.entity.product.ProductSkuEntity skuEntity = productSkuRepository.findById(id).get();
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
        return RestResult.success();
    }

    @Override
    public ProductSkuDTO findById(Integer id) {
        com.mall.dao.entity.product.ProductSkuEntity entity = productSkuRepository.findById(id).get();
        ProductSkuDTO result = new ProductSkuDTO();
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
        com.mall.dao.entity.product.ProductEntity productEntity = productRepository.findById(entity.getProductId()).get();
        result.setProductName(productEntity.getProductName());
        //获取商品可选属性值
        List<com.mall.dao.entity.product.ProductPropertyValueEntity> propertyValues= productPropertyValueRepository.findByProductId(productEntity.getProductId());
        List<Integer> propertiesInt = propertyValues.stream()
                .filter(productPropertyValueEntity -> com.mall.dao.entity.product.ProductPropertyValueEntity.IS_SALE.equals(productPropertyValueEntity.getIsSale()))
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
        com.mall.dao.entity.product.ProductTypeEntity typeEntity = productTypeRepository.findById(productEntity.getTypeId()).get();
        result.setTypeName(typeEntity.getTypeName());
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        productSkuMapper.updateIsDeleteById(id);
    }

    @Override
    public List<com.mall.dao.entity.product.ProductSkuEntity> findList(com.mall.dao.entity.product.ProductSkuEntity entity) {
        Example<com.mall.dao.entity.product.ProductSkuEntity> example = Example.of(entity);
        List<com.mall.dao.entity.product.ProductSkuEntity> result = productSkuRepository.findAll(example);
        return result;
    }

    @Override
    public Page<com.mall.dao.entity.product.ProductSkuEntity> findPage(com.mall.dao.entity.product.ProductSkuEntity entity, Pageable page) {
        Example<com.mall.dao.entity.product.ProductSkuEntity> example = Example.of(entity);
        Page<com.mall.dao.entity.product.ProductSkuEntity> result = productSkuRepository.findAll(example, page);
        return result;
    }

    @Override
    public PageInfo<ProductSkuDTO> findPage(ProductSkuDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<ProductSkuDTO> skuVoList = productSkuMapper.getList(dto);
        //将property值转换成对应value值
        for (ProductSkuDTO skuDto: skuVoList){
            this.makePropertyKeyToValue(skuDto);
        }
        PageInfo<ProductSkuDTO> page = new PageInfo<>(skuVoList);
        return page;
    }

    /**
     * 将property值转换成对应value值
     * @param dto
     */
    private void makePropertyKeyToValue(ProductSkuDTO dto){
        StringBuffer propertySb = new StringBuffer();
        List<com.mall.dao.entity.product.ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeIdAndIsDelete(dto.getTypeId(), CommonConstant.NOT_DELETE);
        List<com.mall.dao.entity.product.ProductPropertyValueEntity> valueList = productPropertyValueRepository.findByProductId(dto.getProductId());
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
                for (com.mall.dao.entity.product.ProductPropertyValueEntity valueEntity: valueList) {
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
