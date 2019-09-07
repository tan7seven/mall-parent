package com.mall.malladmin.mapper.product;

import com.mall.malladmin.dto.product.ProductPropertyValueDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
     * 根据商品ID删除商品属性值-删除表信息
     */
    void deleteByProductId(@Param("productId") Integer productId);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Update("UPDATE mall_product_property_name a SET a.is_delete = '1' WHERE a.property_name_id = #{id}")
    int updateIsDelete(@Param("id") Integer id);
}
