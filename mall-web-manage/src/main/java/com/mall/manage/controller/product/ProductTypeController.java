package com.mall.manage.controller.product;

import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.enums.ResultStatus;
import com.mall.common.vo.RestPage;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.manage.model.param.product.productType.TypeCreateParam;
import com.mall.manage.model.param.product.productType.TypeShowedUpdateParam;
import com.mall.manage.model.param.product.productType.TypeUpdateParam;
import com.mall.manage.model.param.product.productType.TypeUsableUpdateParam;
import com.mall.manage.model.vo.product.type.ProductTypeDetailVO;
import com.mall.manage.model.vo.product.type.ProductTypePageVO;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "商品类目", tags = "商品类目")
@Slf4j
@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @Resource(name = "productPropertyNameService")
    private ProductAttrNameService productPropertyNameService;


    @ApiOperation("分页查询")
    @GetMapping("/page/query")
    protected RestResult<RestPage<ProductTypePageVO>> getPage(@ApiParam(value = "类目ID") @RequestParam Long parentId,
                                                              @ApiParam(value = "类目名") @RequestParam(required = false) String typeName,
                                                              @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer page,
                                                              @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize) {
        RestPage<ProductTypePageVO> result = productTypeService.findPage(parentId, typeName, page, pageSize);
        return RestResult.success(result);
    }
    @ApiOperation("详情")
    @GetMapping(value = "/detail/get/{id}")
    protected RestResult<ProductTypeDetailVO> detail(@PathVariable Long id) {
        ProductTypeEntity entity = productTypeService.getById(id);
        if (null == entity) {
            return RestResult.failed(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        ProductTypeDetailVO result = new ProductTypeDetailVO();
        BeanUtils.copyProperties(entity, result);
        return RestResult.success(result);
    }

    @ApiOperation("创建")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create")
    protected RestResult<Boolean> create(@Validated @RequestBody TypeCreateParam param) {
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(param, entity);
        Boolean result = productTypeService.save(entity);
        return RestResult.success(result);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:UPDATE') or hasRole('ADMIN')")
    @DeleteMapping(value = "/modify")
    protected RestResult<Boolean> modify(@Validated @RequestBody TypeUpdateParam param) {
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(param, entity);
        Boolean result = productTypeService.updateById(entity);
        return RestResult.success(result);
    }

    @ApiOperation("修改是否显示在导航栏")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/show-status/update")
    protected RestResult<Boolean> updateShowed(@Validated @RequestBody TypeShowedUpdateParam param) {
        ProductTypeEntity entity = productTypeService.getById(param.getId());
        if (null == entity) {
            return RestResult.failed(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        entity.setShowed(param.getShowed());
        return RestResult.success(productTypeService.updateById(entity));
    }

    @ApiOperation("修改状态：正常、禁用")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/usable/update")
    protected RestResult updateUsable(@Validated @RequestBody TypeUsableUpdateParam param) {
        ProductTypeEntity entity = productTypeService.getById(param.getId());
        if (null == entity) {
            return RestResult.failed(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        entity.setUsable( param.getUsable());
        return RestResult.success(productTypeService.updateById(entity));
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
