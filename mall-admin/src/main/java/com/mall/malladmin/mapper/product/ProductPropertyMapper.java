package com.mall.malladmin.mapper.product;

import com.mall.malladmin.dto.product.ProductPropertyValueDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductPropertyMapper {
    /**
     * 查询
     * @return
     */
    List<ProductPropertyValueDto> findByPropertyNameIdAndProductId(@Param(value = "propertyNameId")Integer propertyNameId, @Param(value = "productId") Integer productId);

    /**
     * 根据商品ID删除
     */
    void deleteByProductId(@Param("productId") Integer productId);
}
