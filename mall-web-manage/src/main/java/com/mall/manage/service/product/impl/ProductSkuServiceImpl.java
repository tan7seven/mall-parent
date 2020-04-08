package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductService;
import com.mall.manage.service.product.ProductSkuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {

    @Autowired
    private ProductAttrNameService productPropertyNameService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public Page<ProductSkuDTO> findPage(Integer pageNum, Integer pageSize) {
        ProductSkuDTO queryParam = new ProductSkuDTO();
        Page pageParam = new Page<>(pageNum, pageSize);
        Page<ProductSkuDTO> result = productSkuMapper.getList(pageParam, queryParam);
        return result;
    }

    @Override
    public RestResult add(ProductSkuDTO dto) {
        ProductEntity product = productService.getById(dto.getProductId());
        ProductSkuEntity entity = new ProductSkuEntity();
        entity.setProductId(dto.getProductId());
        entity.setStock(dto.getStock()==null?0:dto.getStock());
        entity.setPicUrl(dto.getPicUrl());
        StringBuffer properties = new StringBuffer();
        entity.setAttrJson(properties.toString());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setSellSum(0);
        entity.setDelete(Boolean.FALSE);
        this.save(entity);
        return RestResult.success();
    }

    @Override
    public RestResult update(Long id, ProductSkuDTO dto) {
        dto.setCreateTime(new Date());
        dto.setModifyTime(new Date());
        dto.setStock(dto.getStock()==null?0:dto.getStock());
        dto.setSellSum(dto.getSellSum()==null?0:dto.getSellSum());
        dto.setId(id);
        ProductSkuEntity skuEntity = this.getById(id);
        BeanUtils.copyProperties(dto, skuEntity);
        StringBuffer properties = new StringBuffer();
        skuEntity.setAttrJson(properties.toString());
        this.save(skuEntity);
        return RestResult.success();
    }

    @Override
    public void deleteById(Integer id) {

    }
}
