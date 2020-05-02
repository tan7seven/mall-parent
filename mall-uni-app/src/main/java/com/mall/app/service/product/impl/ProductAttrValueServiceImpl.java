package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductAttrValueService;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrValueMapper;
import org.springframework.stereotype.Service;

@Service(value = "productPropertyValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttrValueService {

}
