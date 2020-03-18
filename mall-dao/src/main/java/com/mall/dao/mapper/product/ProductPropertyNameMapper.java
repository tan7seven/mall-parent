package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.product.ProductPropertyNameDTO;
import com.mall.dao.dto.product.ProductPropertyValueDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductPropertyNameMapper extends BaseMapper<ProductPropertyNameEntity> {

    /**
     * 查询-属性名
     * @param dto
     * @return
     */
    List<ProductPropertyNameDTO> findList(Page page, @Param("dto") ProductPropertyNameDTO dto);
    /**
     * 查询-属性值
     * @return
     */
    List<ProductPropertyValueDTO> findByPropertyNameIdAndProductId(@Param(value = "propertyNameId")Integer propertyNameId, @Param(value = "productId") Integer productId);

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

    /**
     * 修改属性名是否销售属性，对应删除商品属性值跟商品库存
     * @param propertyNameId
     */
    void deleteSku(@Param("propertyNameId") Integer propertyNameId);
    void deleteProperty(@Param("propertyNameId") Integer propertyNameId);
}
