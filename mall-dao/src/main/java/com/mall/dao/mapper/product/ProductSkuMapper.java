package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductSkuEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存信息
 */
public interface ProductSkuMapper extends BaseMapper<ProductSkuEntity> {
    /**
     * 查询库存信息
     * @return
     */
    Page<ProductSkuDTO> getList(Page pageParam, @Param("param") ProductSkuDTO param);
}
