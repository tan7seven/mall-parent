package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrValueMapper;
import com.mall.manage.service.product.ProductAttValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttValueService {
    @Override
    public ProductAttrValueEntity add(ProductAttrValueEntity entity) {
        this.save(entity);
        return entity;
    }

    @Override
    public Optional<ProductAttrValueEntity> getById(Long id) {
        return this.getById(id);
    }

    @Override
    public List<ProductAttrValueEntity> findByProductId(Long productId) {
        return this.findByProductId(productId);
    }

    @Override
    public void delete(ProductAttrValueEntity entity) {
        this.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    @Override
    public List<ProductAttrValueEntity> findList(ProductAttrValueEntity entity) {
        List<ProductAttrValueEntity> result = this.list();
        return result;
    }

    @Override
    public IPage<ProductAttrValueEntity> findPage(ProductAttrValueEntity entity, IPage page) {
        IPage<ProductAttrValueEntity> result = this.page(page);
        return result;
    }
}
