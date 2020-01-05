package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductSkuDTO;
import com.mall.malladmin.service.product.ProductSkuService;
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
    protected CommonResultDTO getPage(ProductSkuDTO dto){
        PageInfo<ProductSkuDTO> result = productSkuService.findPage(dto);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create.do")
    protected CommonResultDTO create (@RequestBody ProductSkuDTO dto){
        log.info("{}",dto);
        if(null == dto.getProductId()){
            return new CommonResultDTO().validateFailed("商品编号为空！");
        }
        if(null == dto.getTypeId()){
            return new CommonResultDTO().validateFailed("商品类目为空！");
        }
        return productSkuService.add(dto);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultDTO update(@PathVariable Integer id, @RequestBody ProductSkuDTO dto){
        log.info("{}",dto);
        if(null == dto.getProductId()){
            return new CommonResultDTO().validateFailed("商品编号为空！");
        }
        return productSkuService.update(id, dto);
    }

    @ApiOperation("根据主键查询")
    @GetMapping(value = "/findById.do/{id}")
    protected CommonResultDTO findById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDTO().validateFailed("SKU编码为空！");
        }
        ProductSkuDTO result = productSkuService.findById(id);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{id}")
    protected CommonResultDTO delete(@PathVariable Integer id){
        productSkuService.deleteById(id);
        return new CommonResultDTO().success();
    }
}
