package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductTypeService;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.dao.mapper.product.ProductTypeMapper;
import org.springframework.stereotype.Service;


@Service(value = "productTypeService")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductTypeEntity> implements ProductTypeService {

}
