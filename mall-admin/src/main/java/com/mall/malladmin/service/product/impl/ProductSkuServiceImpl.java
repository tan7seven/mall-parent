package com.mall.malladmin.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.*;
import com.mall.malladmin.mapper.product.ProductPropertyMapper;
import com.mall.malladmin.mapper.product.ProductSkuMapper;
import com.mall.malladmin.repository.product.*;
import com.mall.malladmin.service.product.ProductSkuService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductSkuVo;
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
    public CommonResultVo add(ProductSkuVo vo) {
        ProductEntity product = productRepository.findById(vo.getProductId()).get();
        if(product.getPriceMin().compareTo(vo.getPrice())== 1){
            return new CommonResultVo().validateFailed("销售价格不能小于商品最低价！");
        }
        ProductSkuEntity entity = new ProductSkuEntity();
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setCost(vo.getCost()==null?new BigDecimal(0):vo.getCost());
        entity.setPrice(vo.getPrice()==null?new BigDecimal(0):vo.getPrice());
        entity.setProductId(vo.getProductId());
        entity.setSellSum(0);
        entity.setStock(vo.getStock()==null?0:vo.getStock());
        StringBuffer properties = new StringBuffer();
        if(StringUtils.isNotBlank(vo.getPropertyValueA())){
            properties.append("&");
            properties.append(vo.getPropertyValueA());
        }
        if(StringUtils.isNotBlank(vo.getPropertyValueB())){
            properties.append("&");
            properties.append(vo.getPropertyValueB());
        }
        if(StringUtils.isNotBlank(vo.getPropertyValueC())){
            properties.append("&");
            properties.append(vo.getPropertyValueC());
        }
        entity.setProperties(properties.toString());
        productSkuRepository.save(entity);
        return new CommonResultVo().success();
    }

    @Override
    public CommonResultVo update(Integer id, ProductSkuVo vo) {
        vo.setCreateTime(new Date());
        vo.setModifyTime(new Date());
        vo.setCost(vo.getCost()==null?new BigDecimal(0):vo.getCost());
        vo.setPrice(vo.getPrice()==null?new BigDecimal(0):vo.getPrice());
        vo.setStock(vo.getStock()==null?0:vo.getStock());
        vo.setSellSum(vo.getSellSum()==null?0:vo.getSellSum());
        vo.setSkuId(id);
        ProductSkuEntity skuEntity = productSkuRepository.findById(id).get();
        BeanUtils.copyProperties(vo, skuEntity);
        StringBuffer properties = new StringBuffer();
        if(StringUtils.isNotBlank(vo.getPropertyValueA())){
            properties.append("&");
            properties.append(vo.getPropertyValueA());
        }
        if(StringUtils.isNotBlank(vo.getPropertyValueB())){
            properties.append("&");
            properties.append(vo.getPropertyValueB());
        }
        if(StringUtils.isNotBlank(vo.getPropertyValueC())){
            properties.append("&");
            properties.append(vo.getPropertyValueC());
        }
        skuEntity.setProperties(properties.toString());
        productSkuRepository.save(skuEntity);
        return new CommonResultVo().success();
    }

    @Override
    public ProductSkuVo findById(Integer id) {
        ProductSkuEntity entity = productSkuRepository.findById(id).get();
        ProductSkuVo result = new ProductSkuVo();
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
    public PageInfo<ProductSkuVo> findPage(ProductSkuVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<ProductSkuVo> skuVoList = productSkuMapper.getList(vo);
        //将property值转换成对应value值
        for (ProductSkuVo skuVo:
                skuVoList) {
            StringBuffer propertySb = new StringBuffer();
            List<ProductPropertyNameEntity> nameList = productPropertyNameRepository.findByTypeId(skuVo.getTypeId());
            List<ProductPropertyValueEntity> valueList = productPropertyValueRepository.findByProductId(skuVo.getProductId());
            if(StringUtils.isNotBlank(skuVo.getProperties())){
                String skuProperties = skuVo.getProperties();
                String[] properties = skuProperties.split("&");
                for (String property:
                        properties) {
                    if(StringUtils.isBlank(property)){
                        continue;
                    }
                    String[] propertyValues = property.split(":");
                    //获取propertyName值
                    for (ProductPropertyNameEntity nameEntity:
                    nameList) {
                        if(propertyValues[0].equals(String.valueOf(nameEntity.getPropertyNameId()))){
                            propertySb.append(nameEntity.getName());
                            propertySb.append("：");
                        }
                    }
                    //获取propertyValue值
                    for (ProductPropertyValueEntity valueEntity:
                            valueList) {
                        if(propertyValues[1].equals(String.valueOf(valueEntity.getPropertyValueId()))){
                            propertySb.append(valueEntity.getValue());
                            propertySb.append("、");
                        }
                    }
                }
                skuVo.setProperties(propertySb.toString());
            }
        }
        PageInfo<ProductSkuVo> page = new PageInfo<>(skuVoList);
        return page;
    }
}
