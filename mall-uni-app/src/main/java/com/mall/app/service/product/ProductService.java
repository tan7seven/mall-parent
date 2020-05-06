package com.mall.app.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.vo.product.ProductDetailVO;
import com.mall.dao.entity.product.ProductEntity;

/**
 * 商品模块
 */
public interface ProductService extends IService<ProductEntity> {

    /**
     * 获取商品详情
     * @param productId
     * @return
     */
    ProductDetailVO getProductDetail(Long productId);

}
