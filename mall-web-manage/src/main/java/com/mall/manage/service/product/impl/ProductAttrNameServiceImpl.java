package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.enums.ResultStatus;
import com.mall.common.exception.BusinessException;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrTypeEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.manage.model.param.product.attr.AttrCreateParam;
import com.mall.manage.model.param.product.attr.AttrShowedUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUsableUpdateParam;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductAttrTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service(value = "productAttrNameService")
public class ProductAttrNameServiceImpl extends ServiceImpl<ProductAttrNameMapper, ProductAttrNameEntity> implements ProductAttrNameService {
    @Autowired
    private ProductAttrTypeService productAttrTypeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAttrName(AttrCreateParam param) {
        ProductAttrTypeEntity typeEntity = productAttrTypeService.getById(param.getTypeId());
        if (Objects.isNull(typeEntity)) {
            throw new BusinessException(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        ProductAttrTypeEntity updateEntity = new ProductAttrTypeEntity();
        updateEntity.setId(typeEntity.getId());
        if (ProductAttrNameTypeEnum.SALE.getCode().equals(param.getInputType())) {
            updateEntity.setAttrNum(typeEntity.getAttrNum()+1);
        }else if(ProductAttrNameTypeEnum.NOT_SALE.getCode().equals(param.getInputType())){
            updateEntity.setParamNum(typeEntity.getParamNum()+1);
        }
        productAttrTypeService.updateById(updateEntity);
        ProductAttrNameEntity entity = new ProductAttrNameEntity();
        BeanUtils.copyProperties(param, entity);
        Boolean result = this.save(entity);
        /** 验证销售属性数量 */
        this.validateAttrNum(param.getTypeId(), param.getType());
        /** 验证属性唯一性 */
        this.validateAttrUnique(param.getTypeId(), param.getName());
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(AttrUpdateParam param) {
        ProductAttrNameEntity entity = new ProductAttrNameEntity();
        BeanUtils.copyProperties(param, entity);
        /** 不允许修改类目 */
        entity.setTypeId(null);
        Boolean result = this.updateById(entity);
        /** 验证属性唯一性 */
        this.validateAttrUnique(param.getTypeId(), param.getName());
        return result;
    }

    @Override
    public Boolean updateIsShow(AttrShowedUpdateParam param) {
        ProductAttrNameEntity entity = new ProductAttrNameEntity();
        entity.setId(param.getId());
        entity.setShowed(param.getShowed());
        Boolean result = this.updateById(entity);
        return result;
    }

    @Override
    public Boolean updateIsSale(AttrUsableUpdateParam param) {
        ProductAttrNameEntity entity =this.baseMapper.selectById(param.getId());
        entity.setId(param.getId());
        entity.setUsable(param.getUsable());
        Boolean result = this.updateById(entity);
        return result;
    }

    /**
     * 验证销售属性数量是否超过三个
     * @param typeId
     * @param type
     */
    private void validateAttrNum(Long typeId, Integer type){
        if(ProductAttrNameTypeEnum.SALE.getCode().equals(type)){
            List<ProductAttrNameEntity>  AttrNameList = this.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                    .eq(ProductAttrNameEntity::getTypeId, typeId)
                    .eq(ProductAttrNameEntity::getType, ProductAttrNameTypeEnum.SALE.getCode()) );
            if(null != AttrNameList && AttrNameList.size() >3){
                throw new BusinessException("同个分类最多只能有三个销售属性！");
            }
        }
    }

    /**
     * 验证属性是否重复
     * @param typeId
     * @param name
     */
    private void validateAttrUnique(Long typeId, String name){
        List<ProductAttrNameEntity>  isExist = this.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId)
                .eq(ProductAttrNameEntity::getName, name));
        if(!CollectionUtils.isEmpty(isExist) && isExist.size() > 1){
            throw new BusinessException("同个分类下属性名不能相同！");
        }
    }

}
