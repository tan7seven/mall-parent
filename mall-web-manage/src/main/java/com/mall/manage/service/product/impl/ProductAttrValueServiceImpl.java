package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrValueMapper;
import com.mall.manage.service.product.ProductAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttrValueService {
}
