package com.mall.app.service.cart;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.param.cart.CartAddParam;
import com.mall.dao.entity.cart.CartEntity;

public interface CartService  extends IService<CartEntity> {
    /**
     * sku添加购物车
     * @param param
     * @return
     */
    Boolean addSKU(CartAddParam param, Long userId);
}
