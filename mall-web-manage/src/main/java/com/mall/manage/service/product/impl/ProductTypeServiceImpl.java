package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.dao.mapper.product.ProductTypeMapper;
import com.mall.manage.model.vo.product.type.ProductTypePageVO;
import com.mall.manage.service.product.ProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    public List<ProductTypeEntity> findList(ProductTypeEntity entity) {
        List<ProductTypeEntity> result = productTypeService.list();
        return result;
    }

    @Override
    public RestPage<ProductTypePageVO> findPage(Long parentId, String typeName, Integer page, Integer pageSize) {
        Page pageParam = new Page(page, pageSize, true);
        QueryWrapper<ProductTypeEntity> wrapper = new QueryWrapper();
        wrapper.eq("parent_id", parentId);
        if (StringUtils.isNoneBlank(typeName)) {
            wrapper.like("type_name", typeName);
        }
        Page<ProductTypeEntity> entityPage = (Page<ProductTypeEntity>) productTypeService.page(pageParam, wrapper);
        RestPage<ProductTypePageVO> result = new RestPage(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());
        List<ProductTypePageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entityPage.getRecords())) {
            for (ProductTypeEntity record : entityPage.getRecords()) {
                ProductTypePageVO vo = new ProductTypePageVO();
                BeanUtils.copyProperties(record, vo);
                vo.setHasChildren(Boolean.TRUE);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return result;
    }

    @Override
    public List<CommonCascaderDTO> getCascader() {
        return productTypeMapper.getCascader();
    }
}
