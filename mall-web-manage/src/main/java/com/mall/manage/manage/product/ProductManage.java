package com.mall.manage.manage.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.mall.common.enums.AttrNameInputTypeEnum;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.service.product.ProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ProductManage {
    @Autowired
    private ProductAttrNameMapper productPropertyMapper;
    @Autowired
    private ProductAttrValueService productAttrValueService;

    public void addAttrValue(CreateParam param, Long productId){
        List<ProductAttrValueEntity> insertList = Lists.newArrayList();
        List<ProductAttrNameEntity> attrNameEntityList = productPropertyMapper.selectList(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, param.getProductTypeId()));
        JSONObject attrValueJSON = JSONObject.parseObject(param.getAttrValueString());
        if (CollectionUtils.isEmpty(attrNameEntityList)) {
            return;
        }
        if (null == attrValueJSON) {
            return;
        }
        for (ProductAttrNameEntity entity : attrNameEntityList) {
            /** 值是字符串 输入类型是手写 */
            if (attrValueJSON.get(String.valueOf(entity.getId())) instanceof String &&
                    AttrNameInputTypeEnum.isInput(entity.getInputType())) {
                String attrValue = (String) attrValueJSON.get(String.valueOf(entity.getId()));
                ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                valueEntity.setNameId(entity.getId());
                valueEntity.setValue(attrValue);
                valueEntity.setProductId(productId);
                insertList.add(valueEntity);
            }
            /** 值是数组 输入类型是选择框（单选 多选） */
            if (attrValueJSON.get(String.valueOf(entity.getId())) instanceof JSONArray &&
                    AttrNameInputTypeEnum.isSelect(entity.getInputType())) {
                List<String> attrValueList = (List<String>) attrValueJSON.get(String.valueOf(entity.getId()));
                for (String attrValue : attrValueList) {
                    ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                    valueEntity.setNameId(entity.getId());
                    valueEntity.setValue(attrValue);
                    valueEntity.setProductId(productId);
                    insertList.add(valueEntity);
                }
            }
        }
        if (!CollectionUtils.isEmpty(insertList)) {
            productAttrValueService.saveBatch(insertList);
        }
    }
}
