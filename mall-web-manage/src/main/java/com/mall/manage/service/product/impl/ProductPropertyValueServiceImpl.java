package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.product.ProductPropertyValueEntity;
import com.mall.dao.mapper.product.ProductPropertyValueMapper;
import com.mall.manage.service.product.ProductPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyValueService")
public class ProductPropertyValueServiceImpl extends ServiceImpl<ProductPropertyValueMapper, ProductPropertyValueEntity> implements ProductPropertyValueService {
    @Override
    public ProductPropertyValueEntity add(ProductPropertyValueEntity entity) {
        this.save(entity);
        return entity;
    }

    @Override
    public Optional<ProductPropertyValueEntity> findById(Integer id) {
        return this.findById(id);
    }

    @Override
    public List<ProductPropertyValueEntity> findByProductId(Integer productId) {
        return this.findByProductId(productId);
    }

    @Override
    public void delete(ProductPropertyValueEntity entity) {
        this.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteById(id);
    }

    @Override
    public List<ProductPropertyValueEntity> findList(ProductPropertyValueEntity entity) {
        List<ProductPropertyValueEntity> result = this.list();
        return result;
    }

    @Override
    public IPage<ProductPropertyValueEntity> findPage(ProductPropertyValueEntity entity, IPage page) {
        IPage<ProductPropertyValueEntity> result = this.page(page);
        return result;
    }
}
