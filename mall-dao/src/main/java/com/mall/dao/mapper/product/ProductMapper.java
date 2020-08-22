package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.entity.product.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 商品信息
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {

    /**
     * 查询
     * @return
     */
    Page<ProductDTO> getList(Page pageParam, @Param("param") ProductDTO param);
}
