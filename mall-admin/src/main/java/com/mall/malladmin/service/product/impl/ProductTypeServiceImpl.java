package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.repository.product.ProductTypeRepository;
import com.mall.malladmin.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductTypeEntity add(ProductTypeEntity entity) {
        return productTypeRepository.save(entity);
    }

    @Override
    public Optional<ProductTypeEntity> findById(Integer id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void delete(ProductTypeEntity entity) {
        productTypeRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productTypeRepository.deleteById(id);
    }

    @Override
    public List<ProductTypeEntity> findList(ProductTypeEntity entity) {
        Example<ProductTypeEntity> example = Example.of(entity);
        List<ProductTypeEntity> result = productTypeRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductTypeEntity> findPage(ProductTypeEntity entity, Pageable page) {
        Example<ProductTypeEntity> example = Example.of(entity);
        Page<ProductTypeEntity> result = productTypeRepository.findAll(example, page);
        return result;
    }
}
