package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductDetailEntity;
import com.mall.malladmin.repository.product.ProductDetailRepository;
import com.mall.malladmin.service.product.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Optional;

public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetailEntity add(ProductDetailEntity entity) {
        return productDetailRepository.save(entity);
    }

    @Override
    public Optional<ProductDetailEntity> findById(Integer id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public void delete(ProductDetailEntity entity) {
        productDetailRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public List<ProductDetailEntity> findList(ProductDetailEntity entity) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("password")//忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("id");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        Example<ProductDetailEntity> example = Example.of(entity);
        List<ProductDetailEntity> result = productDetailRepository.findAll(example);
        return result;
    }
}
