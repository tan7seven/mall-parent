package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.vo.CommonResultVo;
import com.mall.malladmin.vo.product.ProductTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        return new CommonResultVo().success(entity);
    }
    /**
     * 创建
     * @param vo
     * @return
     */
    @PostMapping("/create.do")
    protected CommonResultVo create(ProductTypeVo vo){
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(vo,entity);
        ProductTypeEntity result = productTypeService.add(entity);
        if(result==null){
            return new CommonResultVo().failed();
        }
        if(vo.getPropertyNameChecked().length > 0){
            String[] propertyNames = vo.getPropertyNameChecked();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(result.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.IS_SALE);
                productPropertyNameService.add(propertyName);
            }
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
        ProductTypeEntity entity = productTypeService.findById(vo.getTypeId()).get();
        if(null == entity){
            return new CommonResultVo().validateFailed("typeId异常：获取不到相关类目！");
        }
        BeanUtils.copyProperties(vo,entity);
        productTypeService.add(entity);
        log.info("接收type:{},接收vo:{}",typeId, vo);
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
}
