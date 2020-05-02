package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductTypeEntity;

import java.util.List;

/**
 * 商品类目信息
 */
public interface ProductTypeMapper extends BaseMapper<ProductTypeEntity> {
    /**
     * 获取商品类目的联级信息
     * @return
     */
    List<CommonCascaderDTO> getCascader();
}
