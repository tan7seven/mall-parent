package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品库存信息
 */
public interface ProductSkuMapper extends BaseMapper<ProductSkuEntity> {
    /**
     * 查询库存信息
     * @return
     */
    List<ProductSkuDTO> getList(@Param("dto") ProductSkuDTO dto);

    /**
     * 根据销售属性值删除
     * @param property
     */
    void deleteByIsSaleProperty(@Param(value = "property") String property);

    /**
     * 根据商品编号删除库存信息-逻辑删除
     * @param productId
     * @return
     */
    @Update("UPDATE mall_product_sku a SET a.is_delete = '1' WHERE a.product_id = #{productId}")
    int updateIsDeleteByProductId(@Param("productId") Integer productId);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Update("UPDATE mall_product_sku a SET a.is_delete = '1' WHERE a.sku_id = #{id} ")
    int updateIsDeleteById(@Param("id") Integer id);
}
