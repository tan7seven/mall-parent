package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.repository.product.ProductPropertyNameRepository;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.vo.common.CommonResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "productPropertyNameService")
public class ProductPropertyNameServiceImpl implements ProductPropertyNameService {

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Override
    public CommonResultVo add(ProductPropertyNameEntity entity) {
        if(ProductPropertyNameEntity.IS_SALE.equals(entity.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSale(entity.getTypeId(), ProductPropertyNameEntity.IS_SALE);
            if(null != propertyNameList && propertyNameList.size() >=3){
                return new CommonResultVo().validateFailed("同个分类最多只能有三个小时属性！");
            }
        }
        List<ProductPropertyNameEntity>  typeIdAndNameList = productPropertyNameRepository.findByTypeIdAndName(entity.getTypeId(), entity.getName());
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return new CommonResultVo().validateFailed("同个分类下不能属性名不能相同！");
        }
        ProductPropertyNameEntity result = productPropertyNameRepository.save(entity);
        if(result == null){
            return new CommonResultVo().failed();
        }
        return new CommonResultVo().success();
    }

    @Override
    public Optional<ProductPropertyNameEntity> findById(Integer id) {
        return productPropertyNameRepository.findById(id);
    }

    @Override
    public void delete(ProductPropertyNameEntity entity) {
        productPropertyNameRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyNameRepository.deleteById(id);
    }

    @Override
    public void deleteByTypeId(Integer typeId) {
        productPropertyNameRepository.deleteByTypeId(typeId);
    }

    @Override
    public List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        List<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example);
        return result;
    }

    @Override
    public List<ProductPropertyNameEntity> findByTypeId(Integer typeId) {
        return productPropertyNameRepository.findByTypeId(typeId);
    }

    @Override
    public Page<ProductPropertyNameEntity> findPage(ProductPropertyNameEntity entity, Pageable page) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        Page<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example, page);
        return result;
    }
}
