package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.dto.product.ProductAttrValueDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.param.AttrFindPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductAttrNameMapper extends BaseMapper<ProductAttrNameEntity> {

    /**
     * 查询-属性名
     * @return
     */
    Page<ProductAttrNameDTO> findPage(@RequestParam Page page, @Param("param") AttrFindPageParam param);
    /**
     * 查询-属性值
     * @return
     */
    List<ProductAttrValueDTO> findByPropertyNameIdAndProductId(@Param(value = "propertyNameId")Long propertyNameId, @Param(value = "productId") Long productId);

    /**
     * 根据商品ID删除商品属性值-删除表信息
     */
    void deleteByProductId(@Param("productId") Integer productId);
}
