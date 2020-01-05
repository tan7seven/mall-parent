package com.mall.manage.service.product.impl;

import com.mall.dao.entity.product.ProductImgEntity;
import com.mall.dao.repository.product.ProductImgRepository;
import com.mall.manage.service.product.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productImgService")
public class ProductImgServiceImpl implements ProductImgService {
    @Autowired
    private ProductImgRepository productImgRepository;

    @Override
    public ProductImgEntity add(ProductImgEntity entity) {
        return productImgRepository.save(entity);
    }

    @Override
    public Optional<ProductImgEntity> findById(Integer id) {
        return productImgRepository.findById(id);
    }

    @Override
    public void delete(ProductImgEntity entity) {
        productImgRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productImgRepository.deleteById(id);
    }

    @Override
    public List<ProductImgEntity> findList(ProductImgEntity entity) {
        Example<ProductImgEntity> example = Example.of(entity);
        List<ProductImgEntity> result = productImgRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductImgEntity> findPage(ProductImgEntity entity, Pageable page) {
        Example<ProductImgEntity> example = Example.of(entity);
        Page<ProductImgEntity> result = productImgRepository.findAll(example, page);
        return result;
    }
}
