package com.mall.app.controller.product;

import com.mall.app.model.vo.product.ProductDetailVO;
import com.mall.app.service.product.ProductService;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "商品模块")
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/detail/{productId}")
    @ApiOperation(value = "获取商品详情")
    public RestResult<ProductDetailVO> productDetail(@PathVariable Long productId){
        ProductDetailVO result = productService.getProductDetail(productId);
        return RestResult.success(result);
    }





}
