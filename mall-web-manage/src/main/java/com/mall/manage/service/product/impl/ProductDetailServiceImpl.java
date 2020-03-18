package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.product.ProductDetailEntity;
import com.mall.dao.mapper.product.ProductDetailMapper;
import com.mall.manage.service.product.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productDetailService")
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetailEntity> implements ProductDetailService {

    @Autowired
    private ProductDetailService productDetailService;

    @Override
    public Boolean add(ProductDetailEntity entity) {
        return productDetailService.save(entity);
    }

    @Override
    public Optional<ProductDetailEntity> findById(Integer id) {
        return productDetailService.findById(id);
    }

    @Override
    public void delete(ProductDetailEntity entity) {
        productDetailService.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productDetailService.deleteById(id);
    }

    @Override
    public List<ProductDetailEntity> findList(ProductDetailEntity entity) {
        List<ProductDetailEntity> result = productDetailService.list();
        return result;
    }

    @Override
    public Page<ProductDetailEntity> findPage(Page<ProductDetailEntity> page) {
        Page<ProductDetailEntity> result = (Page<ProductDetailEntity>) productDetailService.page(page);
        return result;
    }
}
