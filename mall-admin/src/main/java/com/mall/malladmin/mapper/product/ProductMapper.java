package com.mall.malladmin.mapper.product;

import com.mall.malladmin.vo.product.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductMapper {
    /**
     * 查询
     * @return
     */
    List<ProductVo> getList(@Param("vo") ProductVo vo);


}
