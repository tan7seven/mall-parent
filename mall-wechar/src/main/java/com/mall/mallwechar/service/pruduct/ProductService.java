package com.mall.mallwechar.service.pruduct;

import com.mall.mallmodel.dto.product.ProductDto;
import com.mall.mallmodel.dto.product.ProductTypeDto;
import com.mall.mallmodel.entity.product.ProductTypeEntity;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductService {

    /**
     * 获取商品分类列表
     * @param dto
     * @return
     */
    List<ProductTypeEntity> getTypeList(ProductTypeDto dto);

    List<ProductDto> getList();
}
