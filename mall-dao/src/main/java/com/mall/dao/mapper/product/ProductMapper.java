package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.dto.product.ProductPropertyDTO;
import com.mall.dao.entity.product.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {
    /**
     * 查询
     * @return
     */
    List<ProductDTO> getList(@Param("param") ProductDTO param);

    /**
     * 查询商品销售属性值
     * @param dto
     * @return
     */
    List<ProductPropertyDTO> findPropertyIsSale(@Param("param") ProductDTO param);
    /**
     * 查询商品非销售属性值
     * @param dto
     * @return
     */
    List<String> findPropertyNotSale(@Param("param") ProductDTO param);

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
    int updateProductIsUsable(@Param("typeId") Long typeId, @Param("usable")Boolean isUsable);

}
