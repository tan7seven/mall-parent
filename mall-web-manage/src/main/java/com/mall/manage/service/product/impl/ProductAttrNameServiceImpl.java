package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.manage.service.product.ProductAttrNameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "productPropertyNameService")
public class ProductAttrNameServiceImpl extends ServiceImpl<ProductAttrNameMapper, ProductAttrNameEntity> implements ProductAttrNameService {

    @Autowired
    private ProductAttrNameService productPropertyNameService;

    @Autowired
    private ProductAttrNameMapper productPropertyMapper;

    @Override
    public RestResult<Boolean> add(ProductAttrNameEntity entity) {
        if(Boolean.TRUE.equals(entity.getUsable())){
            List<ProductAttrNameEntity>  propertyNameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                    .eq(ProductAttrNameEntity::getTypeId, entity.getTypeId())
                    .eq(ProductAttrNameEntity::getType, ProductAttrNameTypeEnum.NOT_SALE.getCode())
                    .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
            if(null != propertyNameList && propertyNameList.size() >=3){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        List<ProductAttrNameEntity>  typeIdAndNameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, entity.getTypeId())
                .eq(ProductAttrNameEntity::getName, entity.getName())
                .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return RestResult.validateFailed("同个分类下属性名不能相同！");
        }
        entity.setDelete(Boolean.FALSE);
        Boolean result = productPropertyNameService.save(entity);
        return RestResult.success(result);
    }


    @Override
    public RestResult update(ProductAttrNameDTO dto) {
        if(Boolean.TRUE.equals(dto.getType())){
            List<ProductAttrNameEntity>  propertyNameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                    .eq(ProductAttrNameEntity::getTypeId, dto.getTypeId())
                    .eq(ProductAttrNameEntity::getType, ProductAttrNameTypeEnum.SALE.getCode())
                    .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
            List<Long> nameIdList = propertyNameList.stream().map(s -> s.getId()).collect(Collectors.toList());
            if(null != propertyNameList
                    && propertyNameList.size() >=3
                    && nameIdList.indexOf(dto.getPropertyNameId()) == -1){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        List<ProductAttrNameEntity>  typeIdAndNameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, dto.getTypeId())
                .eq(ProductAttrNameEntity::getName, dto.getName())
                .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return RestResult.validateFailed("同个分类下属性名不能相同！");
        }
        ProductAttrNameEntity entity = productPropertyNameService.getById(dto.getPropertyNameId());
        if(!entity.getType().equals(dto.getType())){
            /**
             * 修改属性名是否销售属性，对应删除商品属性值跟商品库存
             */
            productPropertyMapper.deleteProperty(dto.getPropertyNameId());
            productPropertyMapper.deleteSku(dto.getPropertyNameId());
        }
        BeanUtils.copyProperties(dto,entity);
        productPropertyNameService.save(entity);
        return RestResult.success();
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyMapper.updateIsDelete(id);
    }

    @Override
    public void updateIsDeleteByTypeId(Long typeId) {
        productPropertyNameService.updateIsDeleteByTypeId(typeId);
    }

    @Override
    public List<ProductAttrNameEntity> findList(ProductAttrNameEntity entity) {
        List<ProductAttrNameEntity> result = productPropertyNameService.list();
        return result;
    }

    @Override
    public List<ProductAttrNameEntity> findByTypeId(Integer typeId) {
        return productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId)
                .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
    }

    @Override
    public Page<ProductAttrNameDTO> findPage(ProductAttrNameDTO dto) {
        Page page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<ProductAttrNameDTO> resultList = productPropertyMapper.findList(page, dto);
        page.setRecords(resultList);
        return page;
    }

    @Override
    public RestResult updateIsSale(ProductAttrNameDTO dto) {
        ProductAttrNameEntity entity = productPropertyNameService.getById(dto.getPropertyNameId());
        if(ProductAttrNameTypeEnum.SALE.getCode().equals(dto.getType())){
            List<ProductAttrNameEntity>  propertyNameList = productPropertyNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                    .eq(ProductAttrNameEntity::getTypeId, dto.getTypeId())
                    .eq(ProductAttrNameEntity::getType, ProductAttrNameTypeEnum.SALE.getCode())
                    .eq(ProductAttrNameEntity::getDelete, Boolean.FALSE));
            if(null != propertyNameList && propertyNameList.size() >=3){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        /**
         * 修改属性名是否销售属性，对应删除商品属性值跟商品库存
         */
        productPropertyMapper.deleteProperty(dto.getPropertyNameId());
        productPropertyMapper.deleteSku(dto.getPropertyNameId());
        entity.setType(dto.getType());
        productPropertyNameService.save(entity);
        return RestResult.success();
    }

    @Override
    public RestResult updateIsShow(ProductAttrNameDTO dto) {
        ProductAttrNameEntity entity = productPropertyNameService.getById(dto.getPropertyNameId());
        entity.setShow(dto.getShow());
        productPropertyNameService.save(entity);
        return RestResult.success();
    }

}
