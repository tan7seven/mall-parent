package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.vo.RestPage;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.dto.product.ProductTypeDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductTypeMapper;
import com.mall.manage.model.vo.product.type.ProductTypePageVO;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service(value = "productTypeService")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductTypeEntity> implements ProductTypeService {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Resource(name = "productPropertyNameService")
    private ProductAttrNameService productPropertyNameService;

    @Override
    public ProductTypeEntity add(ProductTypeEntity entity) {
        if(null == entity.getSort()){
            entity.setSort(999);
        }
        productTypeService.save(entity);
        return entity;
    }

    @Override
    public ProductTypeEntity updateType(ProductTypeDTO dto) {
        ProductTypeEntity entity = productTypeService.getById(dto.getTypeId());
        /**
         * 修改对应商品状态
         */
        if(!entity.getUsable().equals(dto.getIsUsable())){
            this.updateTypeIsUsable(dto);
        }
        if ("0".equals(String.valueOf(entity.getParentId()))) {
            productTypeMapper.updateUsableByParent(entity.getId(), dto.getIsUsable());
        }
        BeanUtils.copyProperties(dto,entity);
        if(0 == entity.getParentId()){
            entity.setLevel(0);
        }else{
            ProductTypeEntity parent = productTypeService.getById(entity.getParentId());
            entity.setLevel(parent.getLevel()+1);
        }
        productTypeService.save(entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        productPropertyNameService.updateIsDeleteByTypeId(id);
        List<ProductTypeEntity> entities = productTypeService.list(Wrappers.<ProductTypeEntity>lambdaQuery()
                .eq(ProductTypeEntity::getParentId, id));
        for (ProductTypeEntity entity: entities) {
            productPropertyNameService.updateIsDeleteByTypeId(entity.getId());
        }
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

    /**
     * 修改类目-是否可用
     * 修改对应商品状态-是否可用
     */
    private int updateTypeIsUsable(ProductTypeDTO dto){
        if (Boolean.TRUE.equals(dto.getIsUsable())) {
            return productMapper.updateProductIsUsable(dto.getTypeId(), Boolean.TRUE);
        }else if (Boolean.FALSE.equals(dto.getIsUsable())) {
            return productMapper.updateProductIsUsable(dto.getTypeId(), Boolean.FALSE);
        }
        return 0;
    }
}
