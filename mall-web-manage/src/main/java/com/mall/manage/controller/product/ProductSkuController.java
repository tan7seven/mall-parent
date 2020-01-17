package com.mall.manage.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.service.product.ProductSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "商品SKU", tags = "商品SKU")
@Slf4j
@RestController
@RequestMapping(value = "/productSkuController")
public class ProductSkuController extends GenericController {

    @Resource(name = "productSkuService")
    private ProductSkuService productSkuService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/getPage.do")
    protected RestResult getPage(ProductSkuDTO dto){
        PageInfo<ProductSkuDTO> result = productSkuService.findPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create.do")
    protected RestResult create (@RequestBody ProductSkuDTO dto){
        if(null == dto.getProductId()){
            return RestResult.validateFailed("商品编号为空！");
        }
        if(null == dto.getTypeId()){
            return RestResult.validateFailed("商品类目为空！");
        }
        return productSkuService.add(dto);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update.do/{id}")
    protected RestResult update(@PathVariable Integer id, @RequestBody ProductSkuDTO dto){
        if(null == dto.getProductId()){
            return RestResult.validateFailed("商品编号为空！");
        }
        return productSkuService.update(id, dto);
    }

    @ApiOperation("根据主键查询")
    @GetMapping(value = "/findById.do/{id}")
    protected RestResult findById(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("SKU编码为空！");
        }
        ProductSkuDTO result = productSkuService.findById(id);
        return RestResult.success(result);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{id}")
    protected RestResult delete(@PathVariable Integer id){
        productSkuService.deleteById(id);
        return RestResult.success();
    }
}
