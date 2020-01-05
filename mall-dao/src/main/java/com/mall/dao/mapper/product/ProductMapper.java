package com.mall.dao.mapper.product;

import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.dto.product.ProductPropertyDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductMapper {
    /**
     * 查询
     * @return
     */
    List<ProductDTO> getList(@Param("dto") ProductDTO dto);

    /**
     * 查询商品销售属性值
     * @param dto
     * @return
     */
    List<ProductPropertyDTO> findPropertyIsSale(@Param("dto") ProductDTO dto);
    /**
     * 查询商品非销售属性值
     * @param dto
     * @return
     */
    List<String> findPropertyNotSale(@Param("dto") ProductDTO dto);

    /**
     * 逻辑删除
     * @param productId
     * @return
     */
    @Update("UPDATE mall_product a SET a.is_delete = '1' WHERE a.product_id = #{productId}")
    int updateIsDelete(@Param("productId") Integer productId);

    /**
     * 根据类目ID修改对应商品是否可用
     * @param typeId
     * @param isUsable
     * @return
     */
    int updateProductIsUsable(@Param("typeId") Integer typeId, @Param("isUsable")String isUsable);

}
