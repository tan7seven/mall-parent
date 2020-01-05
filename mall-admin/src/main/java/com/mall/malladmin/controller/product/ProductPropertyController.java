package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductPropertyNameDTO;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
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
    private ProductPropertyNameService productPropertyNameService;

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @ApiOperation("获取详情")
    @GetMapping(value = "/getById.do/{id}")
    protected CommonResultDTO getById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDTO().validateFailed("ID为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(id).get();
        if(null == entity){
            return new CommonResultDTO().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        ProductPropertyNameDTO result = new ProductPropertyNameDTO();
        ProductTypeEntity productType = productTypeService.findById(entity.getTypeId()).get();
        BeanUtils.copyProperties(entity, result);
        result.setParentId(productType.getParentId());
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("分页查询")
    @GetMapping("/getPage.do")
    protected CommonResultDTO getPage(ProductPropertyNameDTO dto){
        PageInfo<ProductPropertyNameDTO> result = productPropertyNameService.findPage(dto);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:CREATE') or hasRole('ADMIN')")
    @PostMapping("/create.do")
    protected CommonResultDTO create(@RequestBody ProductPropertyNameDTO dto){
        ProductPropertyNameEntity entity = new ProductPropertyNameEntity();
        BeanUtils.copyProperties(dto,entity);
        return  productPropertyNameService.add(entity);
    }

    @ApiOperation("更新")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:UPDATE') or hasRole('ADMIN')")
    @PostMapping("/update.do/{id}")
    protected CommonResultDTO update(@PathVariable Integer id, @RequestBody ProductPropertyNameDTO dto){
        if(null == id){
            return new CommonResultDTO().validateFailed("主键为空！");
        }
        return productPropertyNameService.update(dto);
    }

    @ApiOperation("删除-逻辑删除")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:DELETE') or hasRole('ADMIN')")
    @GetMapping("/delete.do/{id}")
    protected CommonResultDTO delete(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDTO().validateFailed("主键为空！");
        }
        productPropertyNameService.deleteById(id);
        return new CommonResultDTO().success();
    }

    @ApiOperation("是否销售属性")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/updateIsSale.do")
    protected CommonResultDTO updateIsSale(@RequestBody ProductPropertyNameDTO dto){
        if(null == dto.getPropertyNameId()){
            return new CommonResultDTO().validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsSale(dto);
    }

    @ApiOperation("是否显示")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTPROPERTY:SWITCH') or hasRole('ADMIN')")
    @PostMapping("/updateIsShow.do")
    protected CommonResultDTO updateIsShow(@RequestBody ProductPropertyNameDTO dto){
        if(null == dto.getPropertyNameId()){
            return new CommonResultDTO().validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsShow(dto);
    }
}
