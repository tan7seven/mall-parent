package com.mall.manage.controller.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.common.enums.ResultStatus;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrTypeEntity;
import com.mall.manage.model.param.product.attr.*;
import com.mall.manage.model.vo.product.attr.AttrNameVO;
import com.mall.manage.model.vo.product.attr.AttrPageVO;
import com.mall.manage.model.vo.product.attr.AttrTypePageVO;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductAttrTypeService;
import com.mall.manage.service.product.ProductAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private ProductAttrTypeService productAttrTypeService;

    @ApiOperation("属性类型-分页查询")
    @GetMapping("/attr-type/page/get")
    protected RestResult<RestPage<AttrTypePageVO>> getPageAttrType(@ApiParam(value = "属性名称") @RequestParam(required = false) String name,
                                                                   @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                                    @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize);
        QueryWrapper<ProductAttrTypeEntity> wrapper = new QueryWrapper();
        if (StringUtils.isNoneBlank(name)) {
            wrapper.like("name", name);
        }
        Page<ProductAttrTypeEntity> attrTypePage = (Page<ProductAttrTypeEntity>) productAttrTypeService.page(pageParam, wrapper);
        RestPage<AttrTypePageVO> result = new RestPage<>(attrTypePage.getCurrent(), attrTypePage.getSize(), attrTypePage.getTotal());
        List<AttrTypePageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(attrTypePage.getRecords())) {
            for (ProductAttrTypeEntity record : attrTypePage.getRecords()) {
                AttrTypePageVO vo = new AttrTypePageVO();
                BeanUtils.copyProperties(record, vo);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return RestResult.success(result);
    }
    @ApiOperation("属性类型-新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:CREATE') or hasRole('ADMIN')")
    @PostMapping("/attr-type/create")
    protected RestResult<Boolean> createAttrType(@Validated @RequestBody AttrTypeCreateParam param){
        ProductAttrTypeEntity typeEntity = new ProductAttrTypeEntity();
        typeEntity.setName(param.getName());
        Boolean result;
        if (null == param.getId()) {
            result = productAttrTypeService.save(typeEntity);
        }else{
            typeEntity.setId(param.getId());
            result = productAttrTypeService.updateById(typeEntity);
        }
        return RestResult.success(result);
    }
    @ApiOperation("分页查询")
    @GetMapping("/page/get")
    protected RestResult<RestPage<AttrPageVO>> getPage(@ApiParam(value = "类目ID") @RequestParam Long typeId,
                                                       @ApiParam(value = "类型") @RequestParam Integer type,
                                                       @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                       @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize);
        Page<ProductAttrNameEntity> entityPage = (Page<ProductAttrNameEntity>) productAttrNameService.page(pageParam, Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId)
                .eq(ProductAttrNameEntity::getType, type));
        RestPage<AttrPageVO> result = new RestPage<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
        List<AttrPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entityPage.getRecords())) {
            for (ProductAttrNameEntity record : entityPage.getRecords()) {
                AttrPageVO vo = new AttrPageVO();
                BeanUtils.copyProperties(record, vo);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
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
    protected RestResult<AttrNameVO> getDetail(@PathVariable Integer id){
        ProductAttrNameEntity entity = productAttrNameService.getById(id);
        if(null == entity){
            return RestResult.failed(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        AttrNameVO result = new AttrNameVO();
        BeanUtils.copyProperties(entity, result);
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

    @ApiOperation("是否可用")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/usable/update")
    protected RestResult updateIsSale(@Validated @RequestBody AttrUsableUpdateParam param){
        Boolean result = productAttrNameService.updateIsSale(param);
        return RestResult.success(result);
    }

    @ApiOperation("根据类目ID获取属性")
    @GetMapping(value = "/type-id/get")
    protected RestResult<List<AttrNameVO>> getByTypeId(@ApiParam(value = "类目ID")@RequestParam Long typeId){
        List<ProductAttrNameEntity> entityList =  productAttrNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId));
        List<AttrNameVO> result = Lists.newArrayList();
        for (ProductAttrNameEntity entity : entityList) {
            AttrNameVO vo = new AttrNameVO();
            BeanUtils.copyProperties(entity, vo);
            result.add(vo);
        }
        return RestResult.success(result);
    }

    @ApiOperation("根据商品ID获取销售属性")
    @GetMapping(value = "/product-id/get")
    protected RestResult<List<AttrValueVO>> getByProductId(@ApiParam(value = "商品ID")@RequestParam Long productId){
        List<AttrValueVO> result = productAttrValueService.getByProductId(productId);
        return RestResult.success(result);
    }
}
