package com.mall.manage.controller.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.model.vo.product.sku.SkuPageVO;
import com.mall.manage.service.product.ProductSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "商品SKU", tags = "商品SKU")
@Slf4j
@RestController
@RequestMapping(value = "/product-sku")
public class ProductSkuController extends GenericController {

    @Resource(name = "productSkuService")
    private ProductSkuService productSkuService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/page/get")
    protected RestResult getPage(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                 @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page<ProductSkuDTO> dtoPage = productSkuService.findPage(pageNum, pageSize);
        RestPage<SkuPageVO> result = new RestPage(dtoPage.getCurrent(), dtoPage.getSize(), dtoPage.getTotal());
        List<SkuPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoPage.getRecords())) {
            for (ProductSkuDTO record : dtoPage.getRecords()) {
                SkuPageVO vo = new SkuPageVO();
                BeanUtils.copyProperties(record, vo);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/createProduct.do")
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
    @PostMapping(value = "/updateType.do/{id}")
    protected RestResult update(@PathVariable Integer id, @RequestBody ProductSkuDTO dto){
        if(null == dto.getProductId()){
            return RestResult.validateFailed("商品编号为空！");
        }
        return RestResult.failed();
    }

    @ApiOperation("根据主键查询")
    @GetMapping(value = "/foundById.do/{id}")
    protected RestResult getById(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("SKU编码为空！");
        }
        return RestResult.success();
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{id}")
    protected RestResult delete(@PathVariable Integer id){
        productSkuService.deleteById(id);
        return RestResult.success();
    }
}
