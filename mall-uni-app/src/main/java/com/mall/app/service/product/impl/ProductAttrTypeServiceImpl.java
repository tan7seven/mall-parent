package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.product.ProductAttrTypeService;
import com.mall.dao.entity.product.ProductAttrTypeEntity;
import com.mall.dao.mapper.product.ProductAttrTypeMapper;
import org.springframework.stereotype.Service;

@Service(value = "productPropertyTypeService")
public class ProductAttrTypeServiceImpl extends ServiceImpl<ProductAttrTypeMapper, ProductAttrTypeEntity> implements ProductAttrTypeService {

}
