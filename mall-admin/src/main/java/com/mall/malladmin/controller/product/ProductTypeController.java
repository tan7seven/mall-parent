package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductTypeService;
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
        return new CommonResultVo().success();
    }
    @GetMapping("/getList.do/{id}")
    protected CommonResultVo getList(@PathVariable Long id,ProductTypeVo vo){
        log.info("接收id:{},接收vo:{}",id, vo);
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        Pageable page = (Pageable) PageRequest.of(vo.getPageNum(), vo.getPageSize(), sort);
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(vo,entity);
        Page<ProductTypeEntity> resultPage = productTypeService.findPage(entity, page);
        return new CommonResultVo().pageSuccess(resultPage.getContent());
    }
}
