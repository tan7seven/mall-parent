package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductSkuEntity;
import com.mall.malladmin.repository.product.ProductSkuRepository;
import com.mall.malladmin.service.product.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productSkuService")
public class ProductSkuServiceImpl implements ProductSkuService {
    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Override
    public ProductSkuEntity add(ProductSkuEntity entity) {
        return productSkuRepository.save(entity);
    }

    @Override
    public Optional<ProductSkuEntity> findById(Integer id) {
        return productSkuRepository.findById(id);
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
}
