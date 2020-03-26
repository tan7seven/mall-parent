package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.exception.BusinessException;
import com.mall.common.vo.RestPage;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.dao.param.AttrFindPageParam;
import com.mall.manage.model.param.product.attr.AttrCreateParam;
import com.mall.manage.model.param.product.attr.AttrShowedUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUsableUpdateParam;
import com.mall.manage.model.vo.product.attr.AttrPageVO;
import com.mall.manage.service.product.ProductAttrNameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service(value = "productAttrNameService")
public class ProductAttrNameServiceImpl extends ServiceImpl<ProductAttrNameMapper, ProductAttrNameEntity> implements ProductAttrNameService {

    @Override
    public RestPage<AttrPageVO> findPage(String typeName, Long typeId, String name, Integer page, Integer pageSize) {
        Page pageParam = new Page<>(page, pageSize);
        AttrFindPageParam param = new AttrFindPageParam();
        if (StringUtils.isNoneBlank(name)) {
            param.setName(name);
        }
        if (StringUtils.isNoneBlank(typeName)) {
            param.setTypeName(typeName);
        }
        if (null != typeId) {
            param.setTypeId(typeId);
        }
        Page<ProductAttrNameDTO> dtoPage = this.baseMapper.findPage(pageParam, param);
        RestPage<AttrPageVO> result = new RestPage<>(dtoPage.getCurrent(), dtoPage.getSize(), dtoPage.getTotal());
        List<AttrPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoPage.getRecords())) {
            for (ProductAttrNameDTO record : dtoPage.getRecords()) {
                AttrPageVO vo = new AttrPageVO();
                BeanUtils.copyProperties(record, vo);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAttrName(AttrCreateParam param) {
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
        Boolean result = this.updateById(entity);
        /** 验证销售属性数量 */
        this.validateAttrNum(param.getTypeId(), param.getType());
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
        /** 验证销售属性数量 */
        this.validateAttrNum(entity.getTypeId(), entity.getType());
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

    //todo done
    @Override
    public List<ProductAttrNameEntity> findByTypeId(Integer typeId) {
        return this.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId) );
    }






}
