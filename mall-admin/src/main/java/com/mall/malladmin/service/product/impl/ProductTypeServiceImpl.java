package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.mapper.product.ProductTypeMapper;
import com.mall.malladmin.repository.product.ProductTypeRepository;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import com.mall.malladmin.vo.common.CommonCascaderVo;
import com.mall.malladmin.vo.product.ProductTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
@Service(value = "productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Resource(name = "productPropertyNameService")
    private ProductPropertyNameService productPropertyNameService;

    @Override
    public ProductTypeEntity add(ProductTypeEntity entity) {
        if(null == entity.getSort()){
            entity.setSort(999);
        }
        return productTypeRepository.save(entity);
    }

    @Override
    public ProductTypeEntity update(ProductTypeVo vo) {
        ProductTypeEntity entity = productTypeRepository.findById(vo.getTypeId()).get();
        if(null != vo.getPropertyNameCheckedIsSale() && vo.getPropertyNameCheckedIsSale().length > 0){
            String[] propertyNames = vo.getPropertyNameCheckedIsSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(entity.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.IS_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        if(null != vo.getPropertyNameCheckedNotSale() && vo.getPropertyNameCheckedNotSale().length > 0){
            String[] propertyNames = vo.getPropertyNameCheckedNotSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(entity.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.NOT_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        BeanUtils.copyProperties(vo,entity);
        return productTypeRepository.save(entity);
    }

    @Override
    public ProductTypeEntity create(ProductTypeVo vo) {
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(vo, entity);
        ProductTypeEntity result = productTypeRepository.save(entity);
        if(0 == entity.getParentId()){
            entity.setLevel(0);
        }else{
            ProductTypeEntity parent = productTypeRepository.findById(entity.getParentId()).get();
            entity.setLevel(parent.getLevel()+1);
        }
        //添加销售属性
        if(vo.getPropertyNameCheckedIsSale().length > 0){
            String[] propertyNames = vo.getPropertyNameCheckedIsSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(result.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.IS_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        //添加非销售属性
        if(vo.getPropertyNameCheckedNotSale().length > 0){
            String[] propertyNames = vo.getPropertyNameCheckedNotSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(result.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.NOT_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        return result;
    }

    @Override
    public Optional<ProductTypeEntity> findById(Integer id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void delete(ProductTypeEntity entity) {
        productTypeRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        productTypeRepository.deleteById(id);
    }

    @Override
    public List<ProductTypeEntity> findList(ProductTypeEntity entity) {
        Example<ProductTypeEntity> example = Example.of(entity);
        List<ProductTypeEntity> result = productTypeRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductTypeEntity> findPage(ProductTypeEntity entity, Pageable page) {
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("typeName", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("typeName" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("sort");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        Example<ProductTypeEntity> example = Example.of(entity, matcher);
        Page<ProductTypeEntity> result = productTypeRepository.findAll(example, page);
        return result;
    }

    @Override
    public List<CommonCascaderVo> getCascader() {
        return productTypeMapper.getCascader();
    }
}
