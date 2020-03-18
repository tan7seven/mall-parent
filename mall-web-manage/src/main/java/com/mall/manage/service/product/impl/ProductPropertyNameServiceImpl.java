package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.constant.CommonConstant;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductPropertyNameDTO;
import com.mall.dao.entity.product.ProductImgEntity;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.mapper.product.ProductImgMapper;
import com.mall.dao.mapper.product.ProductPropertyNameMapper;
import com.mall.dao.repository.product.ProductPropertyNameRepository;
import com.mall.manage.service.product.ProductPropertyNameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "productPropertyNameService")
public class ProductPropertyNameServiceImpl extends ServiceImpl<ProductPropertyNameMapper,ProductPropertyNameEntity> implements ProductPropertyNameService {

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private ProductPropertyNameMapper productPropertyMapper;

    @Override
    public RestResult add(ProductPropertyNameEntity entity) {
        if(ProductPropertyNameEntity.IS_SALE.equals(entity.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSaleAndIsDelete(entity.getTypeId(), ProductPropertyNameEntity.IS_SALE, CommonConstant.NOT_DELETE);
            if(null != propertyNameList && propertyNameList.size() >=3){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        List<ProductPropertyNameEntity>  typeIdAndNameList = productPropertyNameRepository.findByTypeIdAndNameAndIsDelete(entity.getTypeId(), entity.getName(), CommonConstant.NOT_DELETE);
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return RestResult.validateFailed("同个分类下属性名不能相同！");
        }
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        ProductPropertyNameEntity result = productPropertyNameRepository.save(entity);
        if(result == null){
            return RestResult.failed();
        }
        return RestResult.success();
    }

    @Override
    public Optional<ProductPropertyNameEntity> findById(Integer id) {
        return productPropertyNameRepository.findById(id);
    }

    @Override
    public RestResult update(ProductPropertyNameDTO dto) {
        if(ProductPropertyNameEntity.IS_SALE.equals(dto.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSaleAndIsDelete(dto.getTypeId(), ProductPropertyNameEntity.IS_SALE, CommonConstant.NOT_DELETE);
            List<Integer> nameIdList = propertyNameList.stream().map(s -> s.getPropertyNameId()).collect(Collectors.toList());
            if(null != propertyNameList
                    && propertyNameList.size() >=3
                    && nameIdList.indexOf(dto.getPropertyNameId()) == -1){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        List<ProductPropertyNameEntity>  typeIdAndNameList = productPropertyNameRepository.findByTypeIdAndNameAndIsDelete(dto.getTypeId(), dto.getName(), CommonConstant.NOT_DELETE);
        if(null != typeIdAndNameList && typeIdAndNameList.size() > 0 ){
            return RestResult.validateFailed("同个分类下属性名不能相同！");
        }
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        if(!entity.getIsSale().equals(dto.getIsSale())){
            /**
             * 修改属性名是否销售属性，对应删除商品属性值跟商品库存
             */
            productPropertyMapper.deleteProperty(dto.getPropertyNameId());
            productPropertyMapper.deleteSku(dto.getPropertyNameId());
        }
        BeanUtils.copyProperties(dto,entity);
        productPropertyNameRepository.save(entity);
        return RestResult.success();
    }

    @Override
    public void deleteById(Integer id) {
        productPropertyMapper.updateIsDelete(id);
    }

    @Override
    public void updateIsDeleteByTypeId(Integer typeId) {
        productPropertyNameRepository.updateIsDeleteByTypeId(typeId);
    }

    @Override
    public List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity) {
        Example<ProductPropertyNameEntity> example = Example.of(entity);
        List<ProductPropertyNameEntity> result = productPropertyNameRepository.findAll(example);
        return result;
    }

    @Override
    public List<ProductPropertyNameEntity> findByTypeId(Integer typeId) {
        return productPropertyNameRepository.findByTypeIdAndIsDelete(typeId, CommonConstant.NOT_DELETE);
    }

    @Override
    public Page<ProductPropertyNameDTO> findPage(ProductPropertyNameDTO dto) {
        Page page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<ProductPropertyNameDTO> resultList = productPropertyMapper.findList(page, dto);
        page.setRecords(resultList);
        return page;
    }

    @Override
    public RestResult updateIsSale(ProductPropertyNameDTO dto) {
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        if(ProductPropertyNameEntity.IS_SALE.equals(dto.getIsSale())){
            List<ProductPropertyNameEntity>  propertyNameList = productPropertyNameRepository.findByTypeIdAndIsSaleAndIsDelete(entity.getTypeId(), ProductPropertyNameEntity.IS_SALE, CommonConstant.NOT_DELETE);
            if(null != propertyNameList && propertyNameList.size() >=3){
                return RestResult.validateFailed("同个分类最多只能有三个销售属性！");
            }
        }
        /**
         * 修改属性名是否销售属性，对应删除商品属性值跟商品库存
         */
        productPropertyMapper.deleteProperty(dto.getPropertyNameId());
        productPropertyMapper.deleteSku(dto.getPropertyNameId());
        entity.setIsSale(dto.getIsSale());
        productPropertyNameRepository.save(entity);
        return RestResult.success();
    }

    @Override
    public RestResult updateIsShow(ProductPropertyNameDTO dto) {
        ProductPropertyNameEntity entity = productPropertyNameRepository.findById(dto.getPropertyNameId()).get();
        entity.setIsShow(dto.getIsShow());
        productPropertyNameRepository.save(entity);
        return RestResult.success();
    }

}
