package com.mall.manage.controller.product;

import com.mall.common.enums.ResultStatus;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.manage.model.param.product.type.TypeCreateParam;
import com.mall.manage.model.param.product.type.TypeShowedUpdateParam;
import com.mall.manage.model.param.product.type.TypeUpdateParam;
import com.mall.manage.model.param.product.type.TypeUsableUpdateParam;
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
import java.util.List;

@Api(value = "商品类目", tags = "商品类目")
@Slf4j
@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @Resource(name = "productAttrNameService")
    private ProductAttrNameService productAttrNameService;


    @ApiOperation("分页查询")
    @GetMapping("/page/query")
    protected RestResult<RestPage<ProductTypePageVO>> getPage(@ApiParam(value = "类目ID") @RequestParam Long parentId,
                                                              @ApiParam(value = "类目名") @RequestParam(required = false) String typeName,
                                                              @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                              @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize) {
        RestPage<ProductTypePageVO> result = productTypeService.findPage(parentId, typeName, pageNum, pageSize);
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
    @PostMapping("/createProduct")
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
        Boolean result = productTypeService.updateById(entity);
        return RestResult.success(result);
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
        Boolean result = productTypeService.updateById(entity);
        return RestResult.success(result);
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTTYPE:DELETE') or hasRole('ADMIN')")
    @GetMapping(value = "/delete/{id}")
    protected RestResult<Boolean> detele(@PathVariable Long id) {
        Boolean result = productTypeService.removeById(id);
        return RestResult.success(result);
    }
    @ApiOperation("查询所有一级和子级分类")
    @GetMapping(value = "/cascader/get")
    protected RestResult getCascader() {
        List<CommonCascaderDTO> result = productTypeService.getCascader();
        return RestResult.success(result);
    }
}
