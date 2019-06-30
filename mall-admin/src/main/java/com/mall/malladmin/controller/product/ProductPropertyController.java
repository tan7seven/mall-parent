package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductPropertyNameVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品分类属性
 */
@Slf4j
@RestController
@RequestMapping(value = "productPropertyController")
public class ProductPropertyController {

    @Resource(name = "productPropertyNameService")
    private ProductPropertyNameService productPropertyNameService;

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @GetMapping(value = "/getById.do/{id}")
    protected CommonResultVo getById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("ID为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(id).get();
        if(null == entity){
            return new CommonResultVo().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        ProductPropertyNameVo result = new ProductPropertyNameVo();
        ProductTypeEntity productType = productTypeService.findById(entity.getTypeId()).get();
        BeanUtils.copyProperties(entity, result);
        result.setParentId(productType.getParentId());
        return new CommonResultVo().success(result);
    }

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @GetMapping("/getPage.do")
    protected CommonResultVo getPage(ProductPropertyNameVo vo){
        if(0 == vo.getTypeId()){
            vo.setTypeId(null);
        }
        Sort sort = new Sort(Sort.Direction.ASC, "propertyNameId");
        Pageable page = PageRequest.of(vo.getPageNum()-1, vo.getPageSize(), sort);
        ProductPropertyNameEntity entity = new ProductPropertyNameEntity();
        BeanUtils.copyProperties(vo,entity);
        Page<ProductPropertyNameEntity> result = productPropertyNameService.findPage(entity, page);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultVo().pageSuccess(resultPage);
    }
    /**
     * 新增
     * @return
     */
    @PostMapping("/create.do")
    protected CommonResultVo create(@RequestBody ProductPropertyNameVo vo){
        ProductPropertyNameEntity entity = new ProductPropertyNameEntity();
        BeanUtils.copyProperties(vo,entity);
        return  productPropertyNameService.add(entity);
    }
    /**
     * 更新
     * @return
     */
    @PostMapping("/update.do/{id}")
    protected CommonResultVo update(@PathVariable Integer id, @RequestBody ProductPropertyNameVo vo){
        if(null == id){
            return new CommonResultVo().validateFailed("主键为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(id).get();
        BeanUtils.copyProperties(vo,entity);
        return productPropertyNameService.add(entity);
    }
    /**
     * 删除
     * @return
     */
    @GetMapping("/delete.do/{id}")
    protected CommonResultVo delete(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("主键为空！");
        }
        productPropertyNameService.deleteById(id);
        return new CommonResultVo().success();
    }
    /**
     * 是否销售属性
     * @return
     */
    @PostMapping("/updateIsSale.do")
    protected CommonResultVo updateIsSale(@RequestBody ProductPropertyNameVo vo){
        if(null == vo.getPropertyNameId()){
            return new CommonResultVo().validateFailed("主键为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(vo.getPropertyNameId()).get();
        entity.setIsSale(vo.getIsSale());
        return productPropertyNameService.add(entity);
    }
    /**
     * 是否显示
     * @return
     */
    @PostMapping("/updateIsShow.do")
    protected CommonResultVo updateIsShow(@RequestBody ProductPropertyNameVo vo){
        if(null == vo.getPropertyNameId()){
            return new CommonResultVo().validateFailed("主键为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(vo.getPropertyNameId()).get();
        entity.setIsShow(vo.getIsShow());
        productPropertyNameService.add(entity);
        return new CommonResultVo().success();
    }
    /**
     * 是否可用
     * @return
     */
    @PostMapping("/updateIsUsable.do")
    protected CommonResultVo updateIsUsable(@RequestBody ProductPropertyNameVo vo){
        if(null == vo.getPropertyNameId()){
            return new CommonResultVo().validateFailed("主键为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(vo.getPropertyNameId()).get();
        entity.setIsUsable(vo.getIsUsable());
        productPropertyNameService.add(entity);
        return new CommonResultVo().success();
    }
}
