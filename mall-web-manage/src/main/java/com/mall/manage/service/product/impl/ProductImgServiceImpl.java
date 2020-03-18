package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.product.ProductImgEntity;
import com.mall.dao.mapper.product.ProductImgMapper;
import com.mall.manage.service.product.ProductImgService;
import org.springframework.stereotype.Service;
@Service(value = "productImgService")
public class ProductImgServiceImpl extends ServiceImpl<ProductImgMapper, ProductImgEntity> implements ProductImgService {
}
