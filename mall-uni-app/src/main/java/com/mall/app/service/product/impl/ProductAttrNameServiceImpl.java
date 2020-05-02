package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductAttrNameService;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import org.springframework.stereotype.Service;

@Service(value = "productAttrNameService")
public class ProductAttrNameServiceImpl extends ServiceImpl<ProductAttrNameMapper, ProductAttrNameEntity> implements ProductAttrNameService {


}
