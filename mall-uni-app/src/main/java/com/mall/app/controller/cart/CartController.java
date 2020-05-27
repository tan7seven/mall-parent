package com.mall.app.controller.cart;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.app.controller.utils.CartUtil;
import com.mall.app.model.param.cart.CartAddParam;
import com.mall.app.model.param.cart.CartAmountModifyParam;
import com.mall.app.model.param.cart.CartRemoveParam;
import com.mall.app.model.vo.cart.CartListVO;
import com.mall.app.service.cart.CartService;
import com.mall.app.service.product.ProductSkuService;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductSkuService productSkuService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "购物车列表")
    public RestResult<RestPage<CartListVO>> list(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Long userId = 123L;
        Page pageParam = new Page(pageNum, pageSize, false);
        QueryWrapper<CartEntity> wrapper = new QueryWrapper();
        if (null != userId) {
            wrapper.eq("user_id", userId);
        }
        Page<CartEntity> attrTypePage = (Page<CartEntity>) cartService.page(pageParam, wrapper);
        RestPage<CartListVO> result = new RestPage<>(attrTypePage.getCurrent(), attrTypePage.getSize(), attrTypePage.getTotal());
        if (CollectionUtils.isEmpty(attrTypePage.getRecords())) {
            return RestResult.success(result);
        }
        List<Long> skuIdList = attrTypePage.getRecords().stream().map(s -> s.getSkuId()).collect(Collectors.toList());
        List<ProductSkuEntity> skuList = productSkuService.list(Wrappers.<ProductSkuEntity>lambdaQuery()
                .in(ProductSkuEntity::getId, skuIdList));
        result = CartUtil.buildPageVO(attrTypePage, skuList);
        return RestResult.success(result);
    }

    @PutMapping(value = "/add")
    @ApiOperation(value = "添加购物车")
    public RestResult<Boolean> add(@RequestBody @Validated CartAddParam param){
        Long userId = 123L;
        Boolean result  = cartService.addSKU(param, userId);
        return RestResult.success(result);
    }

    @DeleteMapping(value = "/clear")
    @ApiOperation(value = "清空购物车")
    public RestResult<Boolean> clear(){
        Long userId = 123L;
        Boolean result  = cartService.remove(Wrappers.<CartEntity>lambdaQuery()
                .eq(CartEntity :: getUserId, userId));
        return RestResult.success(result);
    }

    @DeleteMapping(value = "/remove")
    @ApiOperation(value = "移除购物车指定SKU")
    public RestResult<Boolean> remove(@RequestBody @Validated CartRemoveParam param){
        Long userId = 123L;
        Boolean result  = cartService.remove(Wrappers.<CartEntity>lambdaQuery()
                .eq(CartEntity::getUserId, userId)
                .in(CartEntity::getSkuId, param.getSkuIdList()));
        return RestResult.success(result);
    }

    @PostMapping(value = "/amount/modify")
    @ApiOperation(value = "修改数量")
    public RestResult<Boolean> amountModify(@RequestBody @Validated CartAmountModifyParam param){
        Long userId = 123L;
        CartEntity update = new CartEntity();
        update.setAmount(param.getAmount());
        Boolean result  = cartService.update(update, Wrappers.<CartEntity>lambdaQuery()
                .eq(CartEntity::getUserId, userId)
                .eq(CartEntity::getSkuId, param.getSkuId()));
        return RestResult.success(result);
    }
}
