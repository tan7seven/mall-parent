package com.mall.malladmin.mapper.product;

import com.mall.malladmin.vo.common.CommonCascaderVo;

import java.util.List;

/**
 * 商品类目信息
 */
public interface ProductTypeMapper {
    /**
     * 获取商品类目的联级信息
     * @return
     */
    List<CommonCascaderVo> getCascader();
}
