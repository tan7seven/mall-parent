package com.mall.manage.controller.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品分类属性
 */
@Api(value = "商品分类属性", tags = "商品分类属性")
@Slf4j
@RestController
@RequestMapping(value = "productPropertyController")
public class ProductPropertyController {

    @Resource(name = "productPropertyNameService")
    private ProductAttrNameService productPropertyNameService;

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @ApiOperation("获取详情")
    @GetMapping(value = "/foundById.do/{id}")
    protected RestResult getById(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("ID为空！");
        }
        ProductAttrNameEntity entity = productPropertyNameService.getById(id);
        if(null == entity){
            return RestResult.validateFailed("ID异常：获取不到对应的类目信息！");
        }
        ProductAttrNameDTO result = new ProductAttrNameDTO();
        ProductTypeEntity productType = productTypeService.getById(entity.getTypeId());
        BeanUtils.copyProperties(entity, result);
        result.setParentId(productType.getParentId());
        return RestResult.success(result);
    }

    @ApiOperation("分页查询")
    @GetMapping("/getPage.do")
    protected RestResult getPage(ProductAttrNameDTO dto){
        Page<ProductAttrNameDTO> result = productPropertyNameService.findPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create.do")
    protected RestResult create(@RequestBody ProductAttrNameDTO dto){
        ProductAttrNameEntity entity = new ProductAttrNameEntity();
        BeanUtils.copyProperties(dto,entity);
        return  productPropertyNameService.add(entity);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:UPDATE') or hasRole('ADMIN')")
    @PostMapping("/updateType.do/{id}")
    protected RestResult update(@PathVariable Integer id, @RequestBody ProductAttrNameDTO dto){
        if(null == id){
            return RestResult.validateFailed("主键为空！");
        }
        return productPropertyNameService.update(dto);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:DELETE') or hasRole('ADMIN')")
    @GetMapping("/delete.do/{id}")
    protected RestResult delete(@PathVariable Integer id){
        if(null == id){
            return RestResult.validateFailed("主键为空！");
        }
        productPropertyNameService.deleteById(id);
        return RestResult.success();
    }

    @ApiOperation("是否销售属性")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/updateIsSale.do")
    protected RestResult updateIsSale(@RequestBody ProductAttrNameDTO dto){
        if(null == dto.getPropertyNameId()){
            return RestResult.validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsSale(dto);
    }

    @ApiOperation("是否显示")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/updateIsShow.do")
    protected RestResult updateIsShow(@RequestBody ProductAttrNameDTO dto){
        if(null == dto.getPropertyNameId()){
            return RestResult.validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsShow(dto);
    }
}
