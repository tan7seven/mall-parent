package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.repository.product.ProductRepository;
import com.mall.malladmin.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity add(ProductEntity entity) {
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
        Example<ProductEntity> example = Example.of(entity);
        Page<ProductEntity> result = productRepository.findAll(example, page);
        return result;
    }
}
