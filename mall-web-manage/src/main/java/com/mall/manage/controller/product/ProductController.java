package com.mall.manage.controller.product;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.manage.UploadPicManage;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.manage.param.product.product.DeleteParam;
import com.mall.manage.param.product.product.GetPageParam;
import com.mall.manage.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@Api(value = "商品信息", tags = "商品信息")
@Slf4j
@RestController
@RequestMapping(value = "/productController")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    @Autowired
    private UploadPicManage uploadPicManage;

    @ApiOperation("分页查询")
    @GetMapping(value = "/getPage.do")
    protected RestResult getPage(GetPageParam param){
        Page<ProductDTO> result = productService.findPage(param);
        return RestResult.success(result);
    }

    @ApiOperation("新建商品信息")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create.do")
    protected RestResult create(@RequestBody ProductDTO dto){
        return productService.create(dto);
    }


    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update.do/{id}")
    protected RestResult update(@PathVariable Integer id , @RequestBody ProductDTO dto){
        if(null == dto.getProductId()){
            return RestResult.validateFailed("商品编号为空！");
        }
        return RestResult.failed();
    }

    @ApiOperation("根据商品名称获取商品列表")
    @PostMapping(value = "/findProductByName.do")
    protected RestResult findProductByName(String name){
        List<ProductDTO> result = productService.findByName(name);
        return RestResult.success(result);
    }
    @ApiOperation("根据ID获取商品信息")
    @GetMapping(value = "/getProduct.do/{id}")
    protected RestResult getProduct(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("id为空！");
        }
        return RestResult.success();
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:DELETE') or hasRole('ADMIN')")
    @DeleteMapping(value = "/delete.do")
    protected RestResult delete(@Validated @RequestBody DeleteParam param){
        RestResult result = productService.deleteList(param.getIds());
        return result;
    }

    @ApiOperation("修改是否上下架状态")
    @PreAuthorize(" hasAuthority('PMS:PRODUCT:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/updateIsPutaway.do")
    protected RestResult updateIsPutaway(@Validated @RequestBody UpdateIsPutawayParam param){
        RestResult result = productService.updateIsPutAway(param);
        return result;
    }
    @ApiOperation("文件上传")
    @PostMapping(value = "/upload.do")
    protected RestResult upload(@RequestParam("picture") MultipartFile picture){
        return uploadPicManage.upload(picture);
    }
}
