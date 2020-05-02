package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.param.AttrFindPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品信息
 */
public interface ProductAttrNameMapper extends BaseMapper<ProductAttrNameEntity> {

}
