package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyNameService")
public class ProductPropertyNameServiceImpl implements ProductPropertyNameService {

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Override
    public ProductPropertyNameEntity add(ProductPropertyNameEntity entity) {
        return productPropertyNameRepository.save(entity);
    }

    @Override
    public Optional<ProductPropertyNameEntity> findById(Integer id) {
        return productPropertyNameRepository.findById(id);
    }

    @Override
    public void delete(ProductPropertyNameEntity entity) {
        productPropertyNameRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyNameRepository.deleteById(id);
    }

    @Override
    public List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        List<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductPropertyNameEntity> findPage(ProductPropertyNameEntity entity, Pageable page) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        Page<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example, page);
        return result;
    }
}
