package com.mall.malladmin.mapper.product;

import com.mall.malladmin.vo.product.ProductSkuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存信息
 */
public interface ProductSkuMapper {
    /**
     * 查询库存信息
     * @return
     */
    List<ProductSkuVo> getList(@Param("vo") ProductSkuVo vo);

    /**
     * 根据销售属性值删除
     * @param property
     */
    void deleteByIsSaleProperty(@Param(value = "property") String property);


}
