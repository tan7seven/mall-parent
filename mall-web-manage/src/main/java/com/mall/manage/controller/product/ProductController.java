package com.mall.manage.controller.product;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.constant.CommonConstant;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.model.param.product.product.DeleteParam;
import com.mall.manage.model.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.model.param.product.product.UpdateParam;
import com.mall.manage.model.vo.product.product.ProductDetailVO;
import com.mall.manage.model.vo.product.product.ProductPageVO;
import com.mall.manage.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Api(value = "商品信息", tags = "商品信息")
@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/page/get")
    protected RestResult<RestPage<ProductPageVO>> getPage(@ApiParam(value = "类目ID") @RequestParam(required = false) Long typeId,
                                                          @ApiParam(value = "商品名称") @RequestParam(required = false) String productName,
                                                          @ApiParam(value = "上下架") @RequestParam(required = false) Boolean putaway,
                                                          @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                          @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page<ProductDTO> dtoPage = productService.findPage(typeId, productName, putaway, pageNum, pageSize);
        RestPage<ProductPageVO> result = new RestPage<>(dtoPage.getCurrent(), dtoPage.getSize(), dtoPage.getTotal());
        List<ProductPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoPage.getRecords())) {
            for (ProductDTO record : dtoPage.getRecords()) {
                ProductPageVO vo = new ProductPageVO();
                BeanUtils.copyProperties(record, vo);
                vo.setPicUrl(CommonConstant.IMG_PRE + vo.getPicUrl());
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return RestResult.success(result);
    }

    @ApiOperation("新建商品信息")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create")
    protected RestResult<Boolean> create(@Validated @RequestBody CreateParam param){
        Boolean result = productService.createProduct(param);
        return RestResult.success(result);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update")
    protected RestResult update(@Validated @RequestBody UpdateParam param){
        Boolean result = productService.updateProduct(param);
        return RestResult.success(result);
    }


    @ApiOperation("修改是否上下架状态")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/putaway/update")
    protected RestResult<Boolean> updateIsPutaway(@Validated @RequestBody UpdateIsPutawayParam param){
        Boolean result = productService.updateIsPutAway(param);
        return RestResult.success(result);
    }

    @ApiOperation("根据ID获取商品信息")
    @GetMapping(value = "/detail/get/{id}")
    protected RestResult<ProductDetailVO> getProductDtail(@PathVariable Long id){
        ProductDetailVO result = productService.getProductDetail(id);
        return RestResult.success(result);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:DELETE') or hasRole('ADMIN')")
    @DeleteMapping(value = "/delete")
    protected RestResult delete(@Validated @RequestBody DeleteParam param){
        Boolean result = productService.deleteList(param.getIds());
        return RestResult.success(result);
    }
}
