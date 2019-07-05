package com.mall.malladmin.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.ProductPropertyDto;
import com.mall.malladmin.entity.product.*;
import com.mall.malladmin.mapper.product.ProductMapper;
import com.mall.malladmin.mapper.product.ProductPropertyMapper;
import com.mall.malladmin.mapper.product.ProductSkuMapper;
import com.mall.malladmin.repository.product.*;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductSkuRepository productSkuRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;
    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductPropertyMapper productPropertyMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public ProductEntity add(ProductEntity entity) {
        entity.setCreateTime(new Date());
        return productRepository.save(entity);
    }

    @Override
    public ProductVo findById(Integer id) {
        ProductEntity entity = productRepository.findById(id).get();
        ProductVo vo = new ProductVo();
        BeanUtils.copyProperties(entity, vo);
        //获取商品销售属性值
        List<ProductPropertyValueEntity> propertyValues= productPropertyValueRepository.findByProductId(id);
        List<Integer> properties = propertyValues.stream()
                .filter(productPropertyValueEntity -> ProductPropertyValueEntity.IS_SALE.equals(productPropertyValueEntity.getIsSale()))
                .map(productPropertyValueEntity->productPropertyValueEntity.getPropertyNameId())
                .distinct().collect(Collectors.toList());
        if(null != properties && !properties.isEmpty() && properties.size()>=1 ){
            vo.setPropertyValueAOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(0), entity.getProductId()));
        }
        if(null != properties && !properties.isEmpty() && properties.size()>=2 ){
            vo.setPropertyValueBOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(1), entity.getProductId()));
        }
        if(null != properties && !properties.isEmpty() && properties.size()>=3 ){
            vo.setPropertyValueCOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(2), entity.getProductId()));
        }
        ProductEntity result = productRepository.findById(id).get();
        ProductTypeEntity typeEntity = productTypeRepository.findById(result.getTypeId()).get();
        BeanUtils.copyProperties(result, vo);
        vo.setTypeName(typeEntity.getTypeName());
        vo.setProductTypeParentId(typeEntity.getParentId());
        vo.setProductTypeId(result.getTypeId());
        //获取商品销售属性值
        ProductVo isSaleParam = new ProductVo();
        isSaleParam.setProductId(vo.getProductId());
        isSaleParam.setIsSale(ProductPropertyNameEntity.IS_SALE);
        List<ProductPropertyDto> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        vo.setProductPropertyIsSaleChecked(propertyIsSaleList.stream().map(ProductPropertyDto::getIsSalePropertyString).toArray(String[] ::new));
        //获取商品销售属性值
        ProductVo notSaleParam = new ProductVo();
        notSaleParam.setProductId(vo.getProductId());
        notSaleParam.setIsSale(ProductPropertyNameEntity.NOT_SALE);
        List<String> propertyNotList = productMapper.findPropertyNotSale(notSaleParam);
        vo.setProductPropertyNotSaleChecked(propertyNotList.stream().toArray(String[] ::new));
        //获取商品明细
        List<ProductDetailEntity> detailEntityList = productDetailRepository.findByProductId(vo.getProductId());
        if (null != detailEntityList && !detailEntityList.isEmpty()) {
            vo.setDetail(detailEntityList.get(0).getDetail());
        }
        log.info("商品信息：{}", vo);
        return vo;
    }

    @Override
    public CommonResultVo deleteList(Integer[] ids) {
        for (Integer id : ids) {
            //删除商品库存信息
            productSkuRepository.deleteByProductId(id);
            //删除商品明细
            productDetailRepository.deleteByProductId(id);
            //删除商品属性值
            productPropertyMapper.deleteByProductId(id);
            //删除商品信息
            productRepository.deleteById(id);
        }
        return new CommonResultVo().success();
    }

    @Override
    public void delete(ProductEntity entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> findList(ProductEntity entity) {
        Example<ProductEntity> example = Example.of(entity);
        List<ProductEntity> result = productRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductEntity> findPage(ProductEntity entity, Pageable page) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("productName" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                ;
        Example<ProductEntity> example = Example.of(entity, matcher);
        Page<ProductEntity> result = productRepository.findAll(example, page);
        return result;
    }

    @Override
    public PageInfo<ProductVo> findPage(ProductVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<ProductVo> productVoList = productMapper.getList(vo);
        PageInfo<ProductVo> page = new PageInfo<>(productVoList);
        return page;
    }

    @Override
    public CommonResultVo create(ProductVo vo) {
        //添加商品信息
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(vo, entity);
        entity.setIsPutaway(ProductEntity.PUT_AWAY);
        entity.setCreateTime(new Date());
        entity.setTypeId(vo.getProductTypeId());
        entity.setHits(0);
        ProductEntity resultProduct = productRepository.save(entity);
        //添加商品明细
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setDetail(vo.getDetail());
        productDetailEntity.setProductId(resultProduct.getProductId());
        productDetailRepository.save(productDetailEntity);
        //添加销售属性值
        String[] propertyIsSales = vo.getProductPropertyIsSaleChecked();

        for (String propertyIsSale: propertyIsSales) {

            String[] proerties = propertyIsSale.split(":");
            if(proerties.length < 2){
                continue;
            }
            List<ProductPropertyNameEntity> propertyNameEntities = productPropertyNameRepository.findByTypeIdAndName(vo.getProductTypeId(), proerties[0]);
            if(null == propertyNameEntities && propertyNameEntities.size() != 1){
                continue;
            }
            String propertyValue =proerties[proerties.length-1];
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.IS_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        //添加非销售属性值
        String[] propertyNotSales = vo.getProductPropertyNotSaleChecked();
        List<ProductPropertyNameEntity> propertyNotSales2 = productPropertyNameRepository.findByTypeIdAndIsSale(vo.getProductTypeId(), ProductPropertyNameEntity.NOT_SALE);
        for (int i=0; i<propertyNotSales.length; i++ ) {
            if(null == propertyNotSales2 || propertyNotSales2.size() < i+1){
                break;
            }
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        return new CommonResultVo().success();
    }

    @Override
    public CommonResultVo update(Integer productId, ProductVo vo) {
        //修改商品信息
        ProductEntity entity = productRepository.findById(productId).get();
        BeanUtils.copyProperties(vo, entity);
        ProductEntity resultProduct = productRepository.save(entity);
        //修改商品明细
        productDetailRepository.deleteByProductId(productId);
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setDetail(vo.getDetail());
        productDetailEntity.setProductId(productId);
        productDetailRepository.save(productDetailEntity);
        //修改销售属性值
        String[] propertyIsSales = vo.getProductPropertyIsSaleChecked();
        //获取商品销售属性值
        ProductVo isSaleParam = new ProductVo();
        isSaleParam.setProductId(vo.getProductId());
        isSaleParam.setIsSale(ProductPropertyNameEntity.IS_SALE);
        List<ProductPropertyDto> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        //判断之前是否存在，存在则不操作，不存在则新增
        for (String propertyIsSale: propertyIsSales) {
            List<String> isExist = propertyIsSaleList.stream().map(ProductPropertyDto::getIsSalePropertyString).filter(s -> s.equals(propertyIsSale)).collect(Collectors.toList());
            if(null != isExist && !isExist.isEmpty()){
                continue;
            }
            String[] proerties = propertyIsSale.split(":");
            if(proerties.length < 2){
                continue;
            }
            List<ProductPropertyNameEntity> propertyNameEntities = productPropertyNameRepository.findByTypeIdAndName(vo.getProductTypeId(), proerties[0]);
            if(null == propertyNameEntities && propertyNameEntities.size() != 1){
                continue;
            }
            String propertyValue =proerties[proerties.length-1];
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.IS_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        //判断之前的值是否被删除，删除需要删除对应的SKU信息
        for (ProductPropertyDto propertyIsSale : propertyIsSaleList) {
            List<String> isExist = Arrays.stream(propertyIsSales).filter(s -> s.equals(propertyIsSale.getIsSalePropertyString())).collect(Collectors.toList());
            if(null != isExist && !isExist.isEmpty()){
                continue;
            }
            //删除SKU
            productSkuMapper.deleteByIsSaleProperty(propertyIsSale.getIsSalePropertyId());
            String[] proerties = propertyIsSale.getIsSalePropertyId().split(":");
            if(proerties.length < 2){
                continue;
            }
            //删除属性值
            productPropertyValueRepository.deleteById(Integer.valueOf(proerties[proerties.length-1]));

        }
        //修改非销售属性值->先删除之前的值再添加
        String[] propertyNotSales = vo.getProductPropertyNotSaleChecked();
        productPropertyValueRepository.deleteByProductIdAndIsSale(productId, ProductPropertyValueEntity.NOT_SALE);
        List<ProductPropertyNameEntity> propertyNotSales2 = productPropertyNameRepository.findByTypeIdAndIsSale(vo.getProductTypeId(), ProductPropertyNameEntity.NOT_SALE);
        for (int i=0; i<propertyNotSales.length; i++ ) {
            if(null == propertyNotSales2 || propertyNotSales2.size() < i+1){
                break;
            }
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        return new CommonResultVo().success();
    }

    @Override
    public CommonResultVo updateIsPutAway(String isPutaway, Integer[] ids) {
        for (Integer id : ids) {
            ProductEntity entity = productRepository.findById(id).get();
            entity.setIsPutaway(isPutaway);
            productRepository.save(entity);
        }
        return new CommonResultVo().success();
    }

    @Override
    public List<ProductVo> findByName(String name) {
        ProductVo vo = new ProductVo();
        if(StringUtils.isNotBlank(name)){
            vo.setProductName(name);
        }
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        return productMapper.getList(vo);
    }
}
