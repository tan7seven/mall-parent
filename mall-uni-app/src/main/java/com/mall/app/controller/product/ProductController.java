package com.mall.app.controller.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.app.controller.product.utils.ProductUtil;
import com.mall.app.model.vo.product.ProductDetailVO;
import com.mall.app.model.vo.product.ProductListVO;
import com.mall.app.model.vo.product.ProductTypeVO;
import com.mall.app.service.product.ProductService;
import com.mall.app.service.product.ProductTypeService;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "商品模块")
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping(value = "/detail/{productId}")
    @ApiOperation(value = "获取商品详情")
    public RestResult<ProductDetailVO> productDetail(@PathVariable Long productId){
        ProductDetailVO result = productService.getProductDetail(productId);
        return RestResult.success(result);
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "根据类目获取商品详情")
    public RestResult<RestPage<ProductListVO>> getProductByTypeId(@ApiParam(value = "类目ID") @RequestParam Long typeId,
                                                                  @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                                  @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize, false);
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.eq("type_id", typeId);
        Page<ProductEntity> productPage = (Page<ProductEntity>) productService.page(pageParam, wrapper);
        return RestResult.success();
    }

    @GetMapping(value = "/type/get")
    @ApiOperation(value = "获取类目列表")
    public RestResult<List<ProductTypeVO>> getTypeList(){
        List<ProductTypeEntity> typeList = productTypeService.list(Wrappers.<ProductTypeEntity>lambdaQuery()
                .eq(ProductTypeEntity::getUsable, Boolean.TRUE)
                .eq(ProductTypeEntity::getShowed, Boolean.TRUE));
        List<ProductTypeVO> result = ProductUtil.buildTypeVO(typeList);
        return RestResult.success(result);
    }




}
