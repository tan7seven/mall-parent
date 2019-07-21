package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.dto.common.CommonCascaderDto;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品类目
 */
@Slf4j
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @Resource(name = "productPropertyNameService")
    private ProductPropertyNameService productPropertyNameService;


    @GetMapping(value = "/getById.do/{id}")
    protected CommonResultDto getById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDto().validateFailed("ID为空！");
        }
        ProductTypeEntity entity = productTypeService.findById(id).get();
        if(null == entity){
            return new CommonResultDto().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        List<ProductPropertyNameEntity> propertyNames = productPropertyNameService.findByTypeId(id);
        ProductTypeDto result = new ProductTypeDto();
        BeanUtils.copyProperties(entity, result);
        result.setPropertyNameCheckedIsSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.IS_SALE)).map(ProductPropertyNameEntity ::getName).toArray(String[]::new));
        result.setPropertyNameCheckedNotSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.NOT_SALE)).map(ProductPropertyNameEntity ::getName).toArray(String[]::new));
        return new CommonResultDto().success(result);
    }

    /**
     * 根据类目ID获取商品属性
     * @param id
     * @return
     */
    @GetMapping(value = "/getProductTypeProperty.do/{id}")
    protected CommonResultDto getProductTypeProperty(@PathVariable Integer id){
        List<ProductPropertyNameEntity> propertyNameEntities = productPropertyNameService.findByTypeId(id);
        List<ProductPropertyNameEntity> isSale = propertyNameEntities.stream().filter(a->a.getIsSale().equals(ProductPropertyNameEntity.IS_SALE)).collect(Collectors.toList());
        List<ProductPropertyNameEntity> notSale = propertyNameEntities.stream().filter(a->a.getIsSale().equals(ProductPropertyNameEntity.NOT_SALE)).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("productPropertyIsSale",isSale);
        result.put("productPropertyNotSale",notSale);
        return new CommonResultDto().success(result);
    }
    /**
     * 创建
     * @param dto
     * @return
     */
    @PostMapping("/create.do")
    protected CommonResultDto create(@RequestBody ProductTypeDto dto){
        ProductTypeEntity result = productTypeService.create(dto);
        if(result==null){
            return new CommonResultDto().failed();
        }
        return new CommonResultDto().success();
    }

    /**
     * 获取类目信息
     * @param id
     * @param dto
     * @return
     */
    @GetMapping("/getPage.do/{id}")
    protected CommonResultDto getPage(@PathVariable Long id, ProductTypeDto dto){
        Sort sort = new Sort(Sort.Direction.ASC, "sort", "typeId");
        Pageable page = PageRequest.of(dto.getPageNum()-1, dto.getPageSize(), sort);
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setParentId(id.intValue());
        Page<ProductTypeEntity> result = productTypeService.findPage(entity, page);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultDto().pageSuccess(resultPage);
    }

    /**
     * 修改
     * @param typeId
     * @param dto
     * @return
     */
    @PostMapping(value = "/updateProductType.do/{typeId}")
    protected CommonResultDto updateProductType(@PathVariable Integer typeId, @RequestBody ProductTypeDto dto){
        productPropertyNameService.deleteByTypeId(typeId);
        productTypeService.update(dto);
        return new CommonResultDto().success();
    }
    /**
     * 修改是否显示在导航栏
     * @param dto
     * @return
     */
    @PostMapping(value = "/update/isNavigationBar.do")
    protected CommonResultDto updateBar(@RequestBody ProductTypeDto dto){
        ProductTypeEntity entity = productTypeService.findById(dto.getTypeId()).get();
        if(null == entity){
            return new CommonResultDto().validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setIsNavigationBar(dto.getIsNavigationBar());
        productTypeService.add(entity);
        return new CommonResultDto().success();
    }

    /**
     * 修改状态：正常、禁用
     * @param dto
     * @return
     */
    @PostMapping(value = "/update/status.do")
    protected CommonResultDto updateStatus(@RequestBody ProductTypeDto dto){
        ProductTypeEntity entity = productTypeService.findById(dto.getTypeId()).get();
        if(null == entity){
            return new CommonResultDto().validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setStatus(dto.getStatus());
        productTypeService.add(entity);
        return new CommonResultDto().success();
    }

    /**
     * 删除
     * @param typeId
     * @return
     */
    @GetMapping(value = "/delete.do/{typeId}")
    protected CommonResultDto detele(@PathVariable Integer typeId){
        if(null == typeId){
            return new CommonResultDto().validateFailed("typeId为空！");
        }
        productTypeService.deleteById(typeId);
        return new CommonResultDto().success();
    }

    /**
     * 查询所有一级和子级分类
     * @return
     */
    @GetMapping(value = "/getProductTypeCascader.do")
    protected CommonResultDto getProductTypeCascader(){
        List<CommonCascaderDto> result = productTypeService.getCascader();
        return new CommonResultDto().success(result);
    }
}
