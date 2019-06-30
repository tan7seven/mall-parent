package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.service.product.ProductSkuService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductSkuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品SKU
 */
@Slf4j
@RestController
@RequestMapping(value = "/productSkuController")
public class ProductSkuController extends GenericController {

    @Resource(name = "productSkuService")
    private ProductSkuService productSkuService;

    /**
     * 分页查询
     * @param vo
     * @return
     */
    @GetMapping(value = "/getPage.do")
    protected CommonResultVo getPage(ProductSkuVo vo){
        PageInfo<ProductSkuVo> result = productSkuService.findPage(vo);
        return new CommonResultVo().success(result);
    }

    /**
     * 新增
     * @param vo
     * @return
     */
    @PostMapping(value = "/create.do")
    protected CommonResultVo create (@RequestBody ProductSkuVo vo){
        log.info("{}",vo);
        if(null == vo.getProductId()){
            return new CommonResultVo().validateFailed("商品编号为空！");
        }
        if(null == vo.getTypeId()){
            return new CommonResultVo().validateFailed("商品类目为空！");
        }
        return productSkuService.add(vo);
    }

    /**
     * 更新
     * @param id
     * @param vo
     * @return
     */
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultVo update(@PathVariable Integer id, @RequestBody ProductSkuVo vo){
        log.info("{}",vo);
        if(null == vo.getProductId()){
            return new CommonResultVo().validateFailed("商品编号为空！");
        }
        return productSkuService.update(id, vo);
    }
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @GetMapping(value = "/findById.do/{id}")
    protected CommonResultVo findById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("SKU编码为空！");
        }
        ProductSkuVo result = productSkuService.findById(id);
        return new CommonResultVo().success(result);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping(value = "/delete.do/{id}")
    protected CommonResultVo delete(@PathVariable Integer id){
        productSkuService.deleteById(id);
        return new CommonResultVo().success();
    }
}
