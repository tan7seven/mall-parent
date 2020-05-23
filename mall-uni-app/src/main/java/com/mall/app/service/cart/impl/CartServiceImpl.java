package com.mall.app.service.cart.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.model.param.cart.CartAddParam;
import com.mall.app.service.cart.CartService;
import com.mall.app.service.product.ProductService;
import com.mall.common.exception.BusinessException;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.cart.CartMapper;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/23
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartEntity> implements CartService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public Boolean addSKU(CartAddParam param, Long userId) {
        ProductSkuEntity skuEntity = productSkuMapper.selectById(param.getSkuId());
        if (null == skuEntity) {
            throw new BusinessException("sku不存在");
        }
        if (param.getAmount() > skuEntity.getStock()) {
            throw new BusinessException(String.format("库存不足，库存数量为%d", skuEntity.getStock()));
        }
        CartEntity cartEntity = new CartEntity();
        cartEntity.setAmount(param.getAmount());
        cartEntity.setProductId(skuEntity.getProductId());
        cartEntity.setUserId(userId);
        cartEntity.setSkuId(param.getSkuId());
        int intNum = this.baseMapper.insert(cartEntity);
        return intNum > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
