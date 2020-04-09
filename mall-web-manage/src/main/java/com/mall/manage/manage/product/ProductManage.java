package com.mall.manage.manage.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.mall.common.enums.AttrNameInputTypeEnum;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.manage.service.product.ProductAttrValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class ProductManage {
    @Autowired
    private ProductAttrNameMapper productPropertyMapper;
    @Autowired
    private ProductAttrValueService productAttrValueService;

    public void saveOrUpdateAttrValue(String attrValueString, Long typeId, Long productId) {
        List<ProductAttrValueEntity> insertList = Lists.newArrayList();
        List<ProductAttrValueEntity> updateList = Lists.newArrayList();
        List<Long> removeList = Lists.newArrayList();
        List<ProductAttrNameEntity> attrNameEntityList = productPropertyMapper.selectList(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, typeId));
        JSONObject attrValueJSON = JSONObject.parseObject(attrValueString);
        if (CollectionUtils.isEmpty(attrNameEntityList)) {
            return;
        }
        if (null == attrValueJSON) {
            return;
        }
        for (ProductAttrNameEntity attrNameEntity : attrNameEntityList) {
            JSONObject attrJSONMap = (JSONObject) attrValueJSON.get(String.valueOf(attrNameEntity.getId()));
            /** 值是字符串 输入类型是手写 */
            if (attrJSONMap.get("value") instanceof String &&
                    AttrNameInputTypeEnum.isString(attrNameEntity.getInputType())) {
                String attrValue = (String) attrJSONMap.get("value");
                ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                valueEntity.setNameId(attrNameEntity.getId());
                valueEntity.setValue(attrValue);
                valueEntity.setProductId(productId);
                if (Objects.nonNull(attrJSONMap.get("id"))  && StringUtils.isNotBlank(attrValue)) {
                    String id = (String) attrJSONMap.get("id");
                    valueEntity.setId(Long.valueOf(id));
                    updateList.add(valueEntity);
                }else if(Objects.nonNull(attrJSONMap.get("id")) && StringUtils.isBlank(attrValue)){
                    String id = (String) attrJSONMap.get("id");
                    valueEntity.setId(Long.valueOf(id));
                    removeList.add(Long.valueOf(id));
                }else{
                    valueEntity.setType(attrNameEntity.getType());
                    insertList.add(valueEntity);
                }
            }
            /** 值是数组 输入类型是选择框（单选 多选） */
            if (attrJSONMap.get("value") instanceof JSONArray &&
                    AttrNameInputTypeEnum.isArray(attrNameEntity.getInputType())) {
                List<String> attrValueList = (List<String>) attrJSONMap.get("value");
                ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                valueEntity.setNameId(attrNameEntity.getId());
                valueEntity.setValue(JSONObject.toJSONString(attrValueList));
                valueEntity.setProductId(productId);
                if (Objects.nonNull(attrJSONMap.get("id")) && !CollectionUtils.isEmpty(attrValueList)) {
                    String id = (String) attrJSONMap.get("id");
                    valueEntity.setId(Long.valueOf(id));
                    updateList.add(valueEntity);
                }else if(Objects.nonNull(attrJSONMap.get("id")) && CollectionUtils.isEmpty(attrValueList)){
                    String id = (String) attrJSONMap.get("id");
                    removeList.add(Long.valueOf(id));
                }else{
                    valueEntity.setType(attrNameEntity.getType());
                    insertList.add(valueEntity);
                }
            }
        }
        if (!CollectionUtils.isEmpty(removeList)) {
            productAttrValueService.removeByIds(removeList);
        }
        if (!CollectionUtils.isEmpty(insertList)) {
            productAttrValueService.saveBatch(insertList);
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            productAttrValueService.updateBatchById(updateList);
        }
    }
}
