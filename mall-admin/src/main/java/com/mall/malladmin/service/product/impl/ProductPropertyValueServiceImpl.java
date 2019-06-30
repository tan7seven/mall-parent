package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import com.mall.malladmin.repository.product.ProductPropertyValueRepository;
import com.mall.malladmin.service.product.ProductPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyValueService")
public class ProductPropertyValueServiceImpl implements ProductPropertyValueService {
    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Override
    public ProductPropertyValueEntity add(ProductPropertyValueEntity entity) {
        return productPropertyValueRepository.save(entity);
    }

    @Override
    public Optional<ProductPropertyValueEntity> findById(Integer id) {
        return productPropertyValueRepository.findById(id);
    }

    @Override
    public List<ProductPropertyValueEntity> findByProductId(Integer productId) {
        return productPropertyValueRepository.findByProductId(productId);
    }

    @Override
    public void delete(ProductPropertyValueEntity entity) {
        productPropertyValueRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyValueRepository.deleteById(id);
    }

    @Override
    public List<ProductPropertyValueEntity> findList(ProductPropertyValueEntity entity) {
        Example<ProductPropertyValueEntity> example = Example.of(entity);
        List<ProductPropertyValueEntity> result = productPropertyValueRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductPropertyValueEntity> findPage(ProductPropertyValueEntity entity, Pageable page) {
        Example<ProductPropertyValueEntity> example = Example.of(entity);
        Page<ProductPropertyValueEntity> result = productPropertyValueRepository.findAll(example, page);
        return result;
    }
}
