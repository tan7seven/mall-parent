package com.mall.malladmin.mapper.product;

import com.mall.malladmin.dto.product.ProductDto;
import com.mall.malladmin.dto.product.ProductPropertyDto;
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
    List<ProductDto> getList(@Param("dto") ProductDto dto);

    /**
     * 查询商品销售属性值
     * @param dto
     * @return
     */
    List<ProductPropertyDto> findPropertyIsSale(@Param("dto") ProductDto dto);
    /**
     * 查询商品非销售属性值
     * @param dto
     * @return
     */
    List<String> findPropertyNotSale(@Param("dto") ProductDto dto);

}
