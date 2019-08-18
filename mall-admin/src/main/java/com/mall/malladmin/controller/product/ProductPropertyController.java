package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductPropertyNameDto;
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
    protected CommonResultDto getById(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDto().validateFailed("ID为空！");
        }
        ProductPropertyNameEntity entity = productPropertyNameService.findById(id).get();
        if(null == entity){
            return new CommonResultDto().validateFailed("ID异常：获取不到对应的类目信息！");
        }
        ProductPropertyNameDto result = new ProductPropertyNameDto();
        ProductTypeEntity productType = productTypeService.findById(entity.getTypeId()).get();
        BeanUtils.copyProperties(entity, result);
        result.setParentId(productType.getParentId());
        return new CommonResultDto().success(result);
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @GetMapping("/getPage.do")
    protected CommonResultDto getPage(ProductPropertyNameDto dto){
        if(0 == dto.getTypeId()){
            dto.setTypeId(null);
        }
        Sort sort = new Sort(Sort.Direction.ASC, "propertyNameId");
        Pageable page = PageRequest.of(dto.getPageNum()-1, dto.getPageSize(), sort);
        ProductPropertyNameEntity entity = new ProductPropertyNameEntity();
        BeanUtils.copyProperties(dto,entity);
        Page<ProductPropertyNameEntity> result = productPropertyNameService.findPage(entity, page);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultDto().pageSuccess(resultPage);
    }
    /**
     * 新增
     * @return
     */
    @PostMapping("/create.do")
    protected CommonResultDto create(@RequestBody ProductPropertyNameDto dto){
        ProductPropertyNameEntity entity = new ProductPropertyNameEntity();
        BeanUtils.copyProperties(dto,entity);
        return  productPropertyNameService.add(entity);
    }
    /**
     * 更新
     * @return
     */
    @PostMapping("/update.do/{id}")
    protected CommonResultDto update(@PathVariable Integer id, @RequestBody ProductPropertyNameDto dto){
        if(null == id){
            return new CommonResultDto().validateFailed("主键为空！");
        }
        productPropertyNameService.update(dto);
        return new CommonResultDto().success();
    }
    /**
     * 删除
     * @return
     */
    @GetMapping("/delete.do/{id}")
    protected CommonResultDto delete(@PathVariable Integer id){
        if(null == id){
            return new CommonResultDto().validateFailed("主键为空！");
        }
        productPropertyNameService.deleteById(id);
        return new CommonResultDto().success();
    }
    /**
     * 是否销售属性
     * @return
     */
    @PostMapping("/updateIsSale.do")
    protected CommonResultDto updateIsSale( ProductPropertyNameDto dto){
        if(null == dto.getPropertyNameId()){
            return new CommonResultDto().validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsSale(dto);
    }
    /**
     * 是否显示
     * @return
     */
    @PostMapping("/updateIsShow.do")
    protected CommonResultDto updateIsShow( ProductPropertyNameDto dto){
        if(null == dto.getPropertyNameId()){
            return new CommonResultDto().validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsShow(dto);
    }
    /**
     * 是否可用
     * @return
     */
    @PostMapping("/updateIsUsable.do")
    protected CommonResultDto updateIsUsable( ProductPropertyNameDto dto){
        if(null == dto.getPropertyNameId()){
            return new CommonResultDto().validateFailed("主键为空！");
        }
        return productPropertyNameService.updateIsUsable(dto);
    }
}
