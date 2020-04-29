package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductSkuService;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.product.ProductSkuMapper;
import org.springframework.stereotype.Service;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {

}
