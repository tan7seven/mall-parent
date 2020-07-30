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
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "商品模块")
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @ApiOperation(value = "获取商品详情")
    @GetMapping(value = "/detail/{productId}")
    public RestResult<ProductDetailVO> productDetail(@PathVariable Long productId){
        ProductDetailVO result = productService.getProductDetail(productId);
        return RestResult.success(result);
    }

    @ApiOperation(value = "根据类目获取商品详情")
    @GetMapping(value = "/list")
    public RestResult<RestPage<ProductListVO>> getProductByTypeId(@ApiParam(value = "类目ID") @RequestParam Long typeId,
                                                                  @ApiParam(value = "排序字段") @RequestParam(defaultValue = "1") Integer sort,
                                                                  @ApiParam(value = "排序方式 1desc 2asc") @RequestParam(defaultValue = "1") Integer order,
                                                                  @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                                  @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize, false);
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.eq("type_id", typeId);
        String sortType = "sort";
        if (sort == 0) {
            sortType = "sort";
        }else if(sort == 1){
            sortType = "sort";
        }else if(sort == 2){
            sortType = "min_price";
        }
        if (order == 1) {
            wrapper.orderByAsc(sortType);
        }else{
            wrapper.orderByDesc(sortType);
        }
        Page<ProductEntity> productPage = (Page<ProductEntity>) productService.page(pageParam, wrapper);
        RestPage<ProductListVO> result = new RestPage<>(productPage.getCurrent(), productPage.getSize(), productPage.getTotal());
        result.setRecords(ProductUtil.buildProductListVO(productPage.getRecords()));
        return RestResult.success(result);
    }

    @ApiOperation(value = "获取类目列表")
    @GetMapping(value = "/type/list")
    public RestResult<List<ProductTypeVO>> getTypeList(){
        List<ProductTypeEntity> typeList = productTypeService.list(Wrappers.<ProductTypeEntity>lambdaQuery()
                .eq(ProductTypeEntity::getUsable, Boolean.TRUE)
                .eq(ProductTypeEntity::getShowed, Boolean.TRUE)
                .orderByAsc(ProductTypeEntity::getSort));
        List<ProductTypeVO> result = ProductUtil.buildTypeVO(typeList);
        return RestResult.success(result);
    }

}
