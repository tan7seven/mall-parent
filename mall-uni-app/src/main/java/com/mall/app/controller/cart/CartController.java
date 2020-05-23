package com.mall.app.controller.cart;

import com.mall.app.model.param.cart.CartAddParam;
import com.mall.app.service.cart.CartService;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/23
 */
@Slf4j
@Api(value = "购物车模块")
@RestController
@RequestMapping(value = "/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping(value = "/add")
    @ApiOperation(value = "添加购物车")
    public RestResult<Boolean> add(@RequestBody @Validated CartAddParam param){
        Boolean result  = cartService.addSKU(param, 123L);
        return RestResult.success(result);
    }
}
