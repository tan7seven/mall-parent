package com.mall.manage.controller.product;

import com.mall.common.enums.ResultStatus;
import com.mall.common.vo.RestPage;
import com.mall.common.vo.RestResult;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.manage.model.param.product.attr.AttrCreateParam;
import com.mall.manage.model.param.product.attr.AttrShowedUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUsableUpdateParam;
import com.mall.manage.model.vo.product.attr.AttrDetailVO;
import com.mall.manage.model.vo.product.attr.AttrPageVO;
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

/**
 * 商品分类属性
 */
@Api(value = "商品分类属性", tags = "商品分类属性")
@Slf4j
@RestController
@RequestMapping(value = "/product-attr")
public class ProductAttrController {

    @Resource(name = "productAttrNameService")
    private ProductAttrNameService productAttrNameService;

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @ApiOperation("分页查询")
    @GetMapping("/page/get")
    protected RestResult<RestPage<AttrPageVO>> getPage(@ApiParam(value = "类目名称") @RequestParam(required = false) String typeName,
                                                       @ApiParam(value = "属性名称") @RequestParam(required = false) String name,
                                                       @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                       @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        RestPage<AttrPageVO> result = productAttrNameService.findPage(typeName, name, pageNum, pageSize);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create")
    protected RestResult<Boolean> create(@Validated @RequestBody AttrCreateParam param){
        Boolean result = productAttrNameService.createAttrName(param);
        return RestResult.success(result);
    }
    @ApiOperation("获取详情")
    @GetMapping(value = "/detail/get/{id}")
    protected RestResult<AttrDetailVO> getDetail(@PathVariable Integer id){
        ProductAttrNameEntity entity = productAttrNameService.getById(id);
        if(null == entity){
            return RestResult.failed(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        AttrDetailVO result = new AttrDetailVO();
        ProductTypeEntity typeEntity = productTypeService.getById(entity.getTypeId());
        BeanUtils.copyProperties(entity, result);
        result.setParentId(typeEntity.getParentId());
        return RestResult.success(result);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:UPDATE') or hasRole('ADMIN')")
    @PostMapping("/update")
    protected RestResult<Boolean> update(@Validated @RequestBody AttrUpdateParam param){
        Boolean result = productAttrNameService.update(param);
        return RestResult.success(result);
    }
    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:DELETE') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    protected RestResult<Boolean> delete(@PathVariable Long id){
        Boolean result = productAttrNameService.removeById(id);
        return RestResult.success(result);
    }

    @ApiOperation("是否显示")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/showed/update")
    protected RestResult updateIsShow(@Validated @RequestBody AttrShowedUpdateParam param){
        Boolean result = productAttrNameService.updateIsShow(param);
        return RestResult.success(result);
    }

    @ApiOperation("是否销售属性")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/usable/update")
    protected RestResult updateIsSale(@Validated @RequestBody AttrUsableUpdateParam param){
        Boolean result = productAttrNameService.updateIsSale(param);
        return RestResult.success(result);
    }
}
