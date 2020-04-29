package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductDetailService;
import com.mall.dao.entity.product.ProductDetailEntity;
import com.mall.dao.mapper.product.ProductDetailMapper;
import org.springframework.stereotype.Service;

@Service(value = "productDetailService")
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetailEntity> implements ProductDetailService {
}
