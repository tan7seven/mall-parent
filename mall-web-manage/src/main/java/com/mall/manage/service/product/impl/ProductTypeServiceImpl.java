package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.dao.mapper.product.ProductTypeMapper;
import com.mall.manage.service.product.ProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "productTypeService")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductTypeEntity> implements ProductTypeService {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public ProductTypeEntity add(ProductTypeEntity entity) {
        if(null == entity.getSort()){
            entity.setSort(999);
        }
        productTypeService.save(entity);
        return entity;
    }
    @Override
    public Page<ProductTypeEntity> findPage(Long parentId, String typeName, Integer page, Integer pageSize) {
        Page pageParam = new Page(page, pageSize, true);
        QueryWrapper<ProductTypeEntity> wrapper = new QueryWrapper();
        wrapper.eq("parent_id", parentId);
        if (StringUtils.isNoneBlank(typeName)) {
            wrapper.like("type_name", typeName);
        }
        wrapper.orderByAsc("sort");
        Page<ProductTypeEntity> result = (Page<ProductTypeEntity>) productTypeService.page(pageParam, wrapper);

        return result;
    }

    @Override
    public List<CommonCascaderDTO> getCascader() {
        return productTypeMapper.getCascader();
    }
}
