package com.mall.malladmin.service.product.impl;

import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.common.CommonCascaderDTO;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductTypeDTO;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.mapper.product.ProductMapper;
import com.mall.malladmin.mapper.product.ProductTypeMapper;
import com.mall.malladmin.repository.product.ProductTypeRepository;
import com.mall.malladmin.service.product.ProductPropertyNameService;
import com.mall.malladmin.service.product.ProductTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Autowired
    private ProductMapper productMapper;

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
    public ProductTypeEntity update(ProductTypeDTO dto) {
        ProductTypeEntity entity = productTypeRepository.findById(dto.getTypeId()).get();
        /*if(null != dto.getPropertyNameCheckedIsSale() && dto.getPropertyNameCheckedIsSale().length > 0){
            String[] propertyNames = dto.getPropertyNameCheckedIsSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(entity.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.IS_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        if(null != dto.getPropertyNameCheckedNotSale() && dto.getPropertyNameCheckedNotSale().length > 0){
            String[] propertyNames = dto.getPropertyNameCheckedNotSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(entity.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.NOT_SALE);
                productPropertyNameService.add(propertyName);
            }
        }*/
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
            ProductTypeEntity parent = productTypeRepository.findById(entity.getParentId()).get();
            entity.setLevel(parent.getLevel()+1);
        }
        return productTypeRepository.save(entity);
    }

    @Override
    public CommonResultDTO updateIsUsable(ProductTypeDTO dto) {
        ProductTypeEntity entity = this.findById(dto.getTypeId()).get();
        if(null == dto.getTypeId()){
            return new CommonResultDTO().validateFailed("类目ID异常：查无数据！");
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
        return new CommonResultDTO().success();
    }

    @Override
    public ProductTypeEntity create(ProductTypeDTO dto) {
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(dto, entity);
        if(0 == entity.getParentId()){
            entity.setLevel(0);
        }else{
            ProductTypeEntity parent = productTypeRepository.findById(entity.getParentId()).get();
            entity.setLevel(parent.getLevel()+1);
        }
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        ProductTypeEntity result = productTypeRepository.save(entity);
        /*//添加销售属性
        if(null != dto.getPropertyNameCheckedIsSale() && dto.getPropertyNameCheckedIsSale().length > 0){
            String[] propertyNames = dto.getPropertyNameCheckedIsSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(result.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.IS_SALE);
                productPropertyNameService.add(propertyName);
            }
        }
        //添加非销售属性
        if(null != dto.getPropertyNameCheckedNotSale() && dto.getPropertyNameCheckedNotSale().length > 0){
            String[] propertyNames = dto.getPropertyNameCheckedNotSale();
            for (int i = 0 ; i <propertyNames.length ; i++){
                ProductPropertyNameEntity propertyName = new ProductPropertyNameEntity();
                propertyName.setName(propertyNames[i]);
                propertyName.setTypeId(result.getTypeId());
                propertyName.setIsSale(ProductPropertyNameEntity.NOT_SALE);
                productPropertyNameService.add(propertyName);
            }
        }*/
        return result;
    }

    @Override
    public Optional<ProductTypeEntity> findById(Integer id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyNameService.updateIsDeleteByTypeId(id);
        List<ProductTypeEntity> entities = productTypeRepository.findByParentId(id);
        for (ProductTypeEntity entity: entities) {
            productPropertyNameService.updateIsDeleteByTypeId(entity.getTypeId());
        }
        productTypeRepository.updateIsDeleteByParentId(id);
        productTypeRepository.updateIsDelete(id);
    }

    @Override
    public List<ProductTypeEntity> findList(ProductTypeEntity entity) {
        Example<ProductTypeEntity> example = Example.of(entity);
        List<ProductTypeEntity> result = productTypeRepository.findAll(example);
        return result;
    }

    @Override
    public Page<ProductTypeEntity> findPage(ProductTypeDTO dto) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort", "typeId");
        Pageable page = PageRequest.of(dto.getPageNum()-1, dto.getPageSize(), sort);
        ProductTypeEntity entity = new ProductTypeEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("typeName", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("typeName" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("sort");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        Example<ProductTypeEntity> example = Example.of(entity, matcher);
        Page<ProductTypeEntity> result = productTypeRepository.findAll(example, page);
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
