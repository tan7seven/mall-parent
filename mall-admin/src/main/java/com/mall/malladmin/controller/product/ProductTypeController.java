package com.mall.malladmin.controller.product;

import com.mall.malladmin.dto.common.CommonCascaderDTO;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductTypeDTO;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
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
    private ProductPropertyNameService productPropertyNameService;

    @ApiOperation("详情")
    @GetMapping(value = "/getById.do/{id}")
    protected CommonResultDTO getById(@PathVariable Integer id) {
        if (null == id) {
            return new CommonResultDTO().validateFailed("ID为空！");
        }
        ProductTypeEntity entity = productTypeService.findById(id).get();
        if (null == entity) {
            return new CommonResultDTO().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        List<ProductPropertyNameEntity> propertyNames = productPropertyNameService.findByTypeId(id);
        ProductTypeDTO result = new ProductTypeDTO();
        BeanUtils.copyProperties(entity, result);
        result.setPropertyNameCheckedIsSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.IS_SALE)).map(ProductPropertyNameEntity::getName).toArray(String[]::new));
        result.setPropertyNameCheckedNotSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.NOT_SALE)).map(ProductPropertyNameEntity::getName).toArray(String[]::new));
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("根据类目ID获取商品属性")
    @GetMapping(value = "/getProductTypeProperty.do/{id}")
    protected CommonResultDTO getProductTypeProperty(@PathVariable Integer id) {
        List<ProductPropertyNameEntity> propertyNameEntities = productPropertyNameService.findByTypeId(id);
        List<ProductPropertyNameEntity> isSale = propertyNameEntities.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.IS_SALE)).collect(Collectors.toList());
        List<ProductPropertyNameEntity> notSale = propertyNameEntities.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.NOT_SALE)).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("productPropertyIsSale", isSale);
        result.put("productPropertyNotSale", notSale);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("创建")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create.do")
    protected CommonResultDTO create(@RequestBody ProductTypeDTO dto) {
        ProductTypeEntity result = productTypeService.create(dto);
        if (result == null) {
            return new CommonResultDTO().failed();
        }
        return new CommonResultDTO().success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/getPage.do/{id}")
    protected CommonResultDTO getPage(@PathVariable Integer id, ProductTypeDTO dto) {
        dto.setParentId(id);
        Page<ProductTypeEntity> result = productTypeService.findPage(dto);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultDTO().pageSuccess(resultPage);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/updateProductType.do/{typeId}")
    protected CommonResultDTO updateProductType(@PathVariable Integer typeId, @RequestBody ProductTypeDTO dto) {
        productTypeService.update(dto);
        return new CommonResultDTO().success();
    }

    @ApiOperation("修改是否显示在导航栏")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/update/isNavigationBar.do")
    protected CommonResultDTO updateBar(@RequestBody ProductTypeDTO dto) {
        ProductTypeEntity entity = productTypeService.findById(dto.getTypeId()).get();
        if (null == entity) {
            return new CommonResultDTO().validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setIsNavigationBar(dto.getIsNavigationBar());
        productTypeService.add(entity);
        return new CommonResultDTO().success();
    }

    @ApiOperation("修改状态：正常、禁用")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/update/status.do")
    protected CommonResultDTO updateStatus(@RequestBody ProductTypeDTO dto) {
        if (null == dto.getTypeId()) {
            return new CommonResultDTO().validateFailed("类目ID为空！");
        }
        return productTypeService.updateIsUsable(dto);
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete.do/{typeId}")
    protected CommonResultDTO detele(@PathVariable Integer typeId) {
        if (null == typeId) {
            return new CommonResultDTO().validateFailed("typeId为空！");
        }
        productTypeService.deleteById(typeId);
        return new CommonResultDTO().success();
    }

    @ApiOperation("查询所有一级和子级分类")
    @GetMapping(value = "/getProductTypeCascader.do")
    protected CommonResultDTO getProductTypeCascader() {
        List<CommonCascaderDTO> result = productTypeService.getCascader();
        return new CommonResultDTO().success(result);
    }
}
