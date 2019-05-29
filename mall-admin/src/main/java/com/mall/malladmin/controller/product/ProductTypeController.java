package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.vo.common.CommonCascaderVo;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    protected CommonResultVo getById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("ID为空！");
        }
        ProductTypeEntity entity = productTypeService.findById(id).get();
        if(null == entity){
            return new CommonResultVo().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        List<ProductPropertyNameEntity> propertyNames = productPropertyNameService.findByTypeId(id);
        ProductTypeVo vo = new ProductTypeVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setPropertyNameCheckedIsSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.IS_SALE)).map(ProductPropertyNameEntity ::getName).toArray(String[]::new));
        vo.setPropertyNameCheckedNotSale(propertyNames.stream().filter(a -> a.getIsSale().equals(ProductPropertyNameEntity.NOT_SALE)).map(ProductPropertyNameEntity ::getName).toArray(String[]::new));
        return new CommonResultVo().success(vo);
    }
    /**
     * 创建
     * @param vo
     * @return
     */
    @PostMapping("/create.do")
    protected CommonResultVo create(ProductTypeVo vo){
        ProductTypeEntity result = productTypeService.create(vo);
        if(result==null){
            return new CommonResultVo().failed();
        }
        return new CommonResultVo().success();
    }

    /**
     * 获取类目信息
     * @param id
     * @param vo
     * @return
     */
    @GetMapping("/getPage.do/{id}")
    protected CommonResultVo getPage(@PathVariable Long id,ProductTypeVo vo){
        log.info("接收id:{},接收vo:{}",id, vo);
        Sort sort = new Sort(Sort.Direction.ASC, "sort", "typeId");
        Pageable page = PageRequest.of(vo.getPageNum()-1, vo.getPageSize(), sort);
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(vo,entity);
        entity.setParentId(id.intValue());
        Page<ProductTypeEntity> result = productTypeService.findPage(entity, page);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultVo().pageSuccess(resultPage);
    }

    /**
     * 修改
     * @param typeId
     * @param vo
     * @return
     */
    @PostMapping(value = "/updateProductType.do/{typeId}")
    protected CommonResultVo updateProductType(@PathVariable Integer typeId, ProductTypeVo vo){
        productPropertyNameService.deleteByTypeId(typeId);
        productTypeService.update(vo);
        return new CommonResultVo().success();
    }
    /**
     * 修改是否显示在导航栏
     * @param vo
     * @return
     */
    @PostMapping(value = "/update/isNavigationBar.do")
    protected CommonResultVo updateBar(ProductTypeVo vo){
        ProductTypeEntity entity = productTypeService.findById(vo.getTypeId()).get();
        if(null == entity){
            return new CommonResultVo().validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setIsNavigationBar(vo.getIsNavigationBar());
        productTypeService.add(entity);
        return new CommonResultVo().success();
    }

    /**
     * 修改状态：正常、禁用
     * @param vo
     * @return
     */
    @PostMapping(value = "/update/status.do")
    protected CommonResultVo updateStatus(ProductTypeVo vo){
        ProductTypeEntity entity = productTypeService.findById(vo.getTypeId()).get();
        if(null == entity){
            return new CommonResultVo().validateFailed("typeId异常：获取不到相关类目！");
        }
        entity.setStatus(vo.getStatus());
        productTypeService.add(entity);
        return new CommonResultVo().success();
    }

    /**
     * 删除
     * @param typeId
     * @return
     */
    @GetMapping(value = "/delete.do/{typeId}")
    protected CommonResultVo detele(@PathVariable Integer typeId){
        if(null == typeId){
            return new CommonResultVo().validateFailed("typeId为空！");
        }
        productTypeService.deleteById(typeId);
        return new CommonResultVo().success();
    }

    /**
     * 查询所有一级和子级分类
     * @return
     */
    @GetMapping(value = "/getProductTypeCascader.do")
    protected CommonResultVo getProductTypeCascader(){
        List<CommonCascaderVo> result = productTypeService.getCascader();
        return new CommonResultVo().success(result);
    }
}
