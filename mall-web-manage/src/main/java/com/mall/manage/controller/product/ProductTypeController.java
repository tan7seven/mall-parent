package com.mall.manage.controller.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.dto.product.ProductTypeDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "商品类目", tags = "商品类目")
@Slf4j
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @Resource(name = "productPropertyNameService")
    private ProductAttrNameService productPropertyNameService;

    @ApiOperation("详情")
    @GetMapping(value = "/foundById.do/{id}")
    protected RestResult getById(@PathVariable Integer id) {
        if (null == id) {
            return RestResult.validateFailed("ID为空！");
        }
        ProductTypeEntity entity = productTypeService.getById(id);
        if (null == entity) {
            return RestResult.validateFailed("ID异常：获取不到对应的类目信息！");
        }
        List<ProductAttrNameEntity> propertyNames = productPropertyNameService.findByTypeId(id);
        ProductTypeDTO result = new ProductTypeDTO();
        BeanUtils.copyProperties(entity, result);
        result.setPropertyNameCheckedIsSale(propertyNames.stream().filter(a -> a.getType().equals(ProductAttrNameTypeEnum.SALE.getCode())).map(ProductAttrNameEntity::getName).toArray(String[]::new));
        result.setPropertyNameCheckedNotSale(propertyNames.stream().filter(a -> a.getType().equals(Boolean.FALSE)).map(ProductAttrNameEntity::getName).toArray(String[]::new));
        return RestResult.success(result);
    }

    @ApiOperation("根据类目ID获取商品属性")
    @GetMapping(value = "/getProductTypeProperty.do/{id}")
    protected RestResult getProductTypeProperty(@PathVariable Integer id) {
        List<ProductAttrNameEntity> propertyNameEntities = productPropertyNameService.findByTypeId(id);
        List<ProductAttrNameEntity> isSale = propertyNameEntities.stream().filter(a -> a.getType().equals(ProductAttrNameTypeEnum.SALE.getCode())).collect(Collectors.toList());
        List<ProductAttrNameEntity> notSale = propertyNameEntities.stream().filter(a -> a.getType().equals(Boolean.FALSE)).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("productPropertyIsSale", isSale);
        result.put("productPropertyNotSale", notSale);
        return RestResult.success(result);
    }

    @ApiOperation("创建")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create.do")
    protected RestResult create(@RequestBody ProductTypeDTO dto) {
        ProductTypeEntity result = productTypeService.create(dto);
        if (result == null) {
            return RestResult.failed();
        }
        return RestResult.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/getPage.do/{id}")
    protected RestResult getPage(@PathVariable Integer id, ProductTypeDTO dto) {
        dto.setParentId(id);
        Page<ProductTypeEntity> result = productTypeService.findPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/updateProductType.do/{typeId}")
    protected RestResult updateProductType(@PathVariable Integer typeId, @RequestBody ProductTypeDTO dto) {
        productTypeService.update(dto);
        return RestResult.success();
    }

    @ApiOperation("修改是否显示在导航栏")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/update/isNavigationBar.do")
    protected RestResult updateBar(@RequestBody ProductTypeDTO dto) {
        ProductTypeEntity entity = productTypeService.getById(dto.getTypeId());
        if (null == entity) {
            return RestResult.validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setShow(dto.getIsNavigationBar());
        productTypeService.add(entity);
        return RestResult.success();
    }

    @ApiOperation("修改状态：正常、禁用")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/update/status.do")
    protected RestResult updateStatus(@RequestBody ProductTypeDTO dto) {
        if (null == dto.getTypeId()) {
            return RestResult.validateFailed("类目ID为空！");
        }
        return productTypeService.updateIsUsable(dto);
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{typeId}")
    protected RestResult detele(@PathVariable Long typeId) {
        if (null == typeId) {
            return RestResult.validateFailed("typeId为空！");
        }
        productTypeService.deleteById(typeId);
        return RestResult.success();
    }

    @ApiOperation("查询所有一级和子级分类")
    @GetMapping(value = "/getProductTypeCascader.do")
    protected RestResult getProductTypeCascader() {
        List<CommonCascaderDTO> result = productTypeService.getCascader();
        return RestResult.success(result);
    }
}
