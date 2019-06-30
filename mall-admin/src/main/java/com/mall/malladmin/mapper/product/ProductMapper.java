package com.mall.malladmin.mapper.product;

import com.mall.malladmin.dto.ProductPropertyDto;
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

    /**
     * 查询商品销售属性值
     * @param vo
     * @return
     */
    List<ProductPropertyDto> findPropertyIsSale(@Param("vo") ProductVo vo);
    /**
     * 查询商品非销售属性值
     * @param vo
     * @return
     */
    List<String> findPropertyNotSale(@Param("vo") ProductVo vo);

}
