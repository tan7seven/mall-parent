package com.mall.malladmin.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.mapper.product.ProductMapper;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.repository.product.ProductRepository;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductEntity add(ProductEntity entity) {
        entity.setAddTime(new Date());
        return productRepository.save(entity);
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        return productRepository.findById(id);
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
    public CommonResultVo create(ProductVo vo) {
        //添加商品信息
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(vo, entity);
        entity.setStatus(ProductEntity.PUT_AWAY);
        entity.setAddTime(new Date());
        entity.setTypeId(vo.getProductTypeId());
        entity.setHits(0);
        ProductEntity resultProduct = productRepository.save(entity);
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
    public List<ProductVo> findByName(String name) {
        ProductVo vo = new ProductVo();
        if(StringUtils.isNotBlank(name)){
            vo.setProductName(name);
        }
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        return productMapper.getList(vo);
    }
}
