package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.constant.CommonConstant;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.dto.product.ProductTypeDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductTypeMapper;
import com.mall.manage.service.product.ProductPropertyNameService;
import com.mall.manage.service.product.ProductTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service(value = "productTypeService")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductTypeEntity> implements ProductTypeService {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Resource(name = "productPropertyNameService")
    private ProductPropertyNameService productPropertyNameService;

    @Override
    public ProductTypeEntity add(ProductTypeEntity entity) {
        if(null == entity.getSort()){
            entity.setSort(999);
        }
        productTypeService.save(entity);
        return entity;
    }

    @Override
    public ProductTypeEntity update(ProductTypeDTO dto) {
        ProductTypeEntity entity = productTypeService.findById(dto.getTypeId()).get();
        /**
         * 修改对应商品状态
         */
        if(!entity.getIsUsable().equals(dto.getIsUsable())){
            this.updateTypeIsUsable(dto);
        }
        if ("0".equals(String.valueOf(entity.getParentId()))) {
            productTypeMapper.updateUsableByParent(entity.getTypeId(), dto.getIsUsable());
        }
        BeanUtils.copyProperties(dto,entity);
        if(0 == entity.getParentId()){
            entity.setLevel(0);
        }else{
            ProductTypeEntity parent = productTypeService.findById(entity.getParentId()).get();
            entity.setLevel(parent.getLevel()+1);
        }
        productTypeService.save(entity);
        return entity;
    }

    @Override
    public RestResult updateIsUsable(ProductTypeDTO dto) {
        ProductTypeEntity entity = this.findById(dto.getTypeId()).get();
        if(null == dto.getTypeId()){
            return RestResult.validateFailed("类目ID异常：查无数据！");
        }
        /**
         * 修改对应商品状态
         */
        if(!entity.getIsUsable().equals(dto.getIsUsable())){
            this.updateTypeIsUsable(dto);
        }
        if ("0".equals(String.valueOf(entity.getParentId()))) {
            productTypeMapper.updateUsableByParent(entity.getTypeId(), dto.getIsUsable());
        }
        entity.setIsUsable(dto.getIsUsable());
        this.add(entity);
        return RestResult.success();
    }

    @Override
    public ProductTypeEntity create(ProductTypeDTO dto) {
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(dto, entity);
        if(0 == entity.getParentId()){
            entity.setLevel(0);
        }else{
            ProductTypeEntity parent = productTypeService.findById(entity.getParentId()).get();
            entity.setLevel(parent.getLevel()+1);
        }
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        productTypeService.save(entity);
        return entity;
    }

    @Override
    public Optional<ProductTypeEntity> findById(Integer id) {
        return productTypeService.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyNameService.updateIsDeleteByTypeId(id);
        List<ProductTypeEntity> entities = productTypeService.list(Wrappers.<ProductTypeEntity>lambdaQuery()
                .eq(ProductTypeEntity::getParentId, id));
        for (ProductTypeEntity entity: entities) {
            productPropertyNameService.updateIsDeleteByTypeId(entity.getTypeId());
        }
    }

    @Override
    public List<ProductTypeEntity> findList(ProductTypeEntity entity) {
        List<ProductTypeEntity> result = productTypeService.list();
        return result;
    }

    @Override
    public Page<ProductTypeEntity> findPage(ProductTypeDTO dto) {
        Page page = new Page(dto.getPageNum()-1, dto.getPageSize());
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        Page<ProductTypeEntity> result = (Page<ProductTypeEntity>) productTypeService.page(page);
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
        if (CommonConstant.IS_USABLE.equals(dto.getIsUsable())) {
            return productMapper.updateProductIsUsable(dto.getTypeId(), CommonConstant.IS_USABLE);
        }else if (CommonConstant.NOT_USABLE.equals(dto.getIsUsable())) {
            return productMapper.updateProductIsUsable(dto.getTypeId(), CommonConstant.NOT_USABLE);
        }
        return 0;
    }
}
