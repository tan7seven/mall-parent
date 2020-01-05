package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductSkuDto;
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
    protected CommonResultDto getPage(ProductSkuDto dto){
        PageInfo<ProductSkuDto> result = productSkuService.findPage(dto);
        return new CommonResultDto().success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create.do")
    protected CommonResultDto create (@RequestBody ProductSkuDto dto){
        log.info("{}",dto);
        if(null == dto.getProductId()){
            return new CommonResultDto().validateFailed("商品编号为空！");
        }
        if(null == dto.getTypeId()){
            return new CommonResultDto().validateFailed("商品类目为空！");
        }
        return productSkuService.add(dto);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultDto update(@PathVariable Integer id, @RequestBody ProductSkuDto dto){
        log.info("{}",dto);
        if(null == dto.getProductId()){
            return new CommonResultDto().validateFailed("商品编号为空！");
        }
        return productSkuService.update(id, dto);
    }

    @ApiOperation("根据主键查询")
    @GetMapping(value = "/findById.do/{id}")
    protected CommonResultDto findById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDto().validateFailed("SKU编码为空！");
        }
        ProductSkuDto result = productSkuService.findById(id);
        return new CommonResultDto().success(result);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{id}")
    protected CommonResultDto delete(@PathVariable Integer id){
        productSkuService.deleteById(id);
        return new CommonResultDto().success();
    }
}
