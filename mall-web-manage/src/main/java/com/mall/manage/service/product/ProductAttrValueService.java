package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.manage.model.vo.product.attr.AttrValueVO;

import java.util.List;

/**
 * 商品属性值
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    /**
     * 获取商品SKU列表
     * @param productId
     * @return
     */
    List<AttrValueVO> getByProductId(Long productId);
}
