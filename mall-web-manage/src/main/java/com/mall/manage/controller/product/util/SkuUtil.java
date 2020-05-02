package com.mall.manage.controller.product.util;

import com.google.common.collect.Lists;
import com.mall.common.exception.BusinessException;
import com.mall.manage.model.param.product.sku.SkuAttrParam;
import com.mall.manage.model.param.product.sku.SkuCreateItemParam;
import com.mall.manage.model.param.product.sku.SkuCreateParam;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
public class SkuUtil {
    public static void validatedCreateParam(SkuCreateParam param){
        List<String> isExist = Lists.newArrayList();
        for (SkuCreateItemParam itemParam : param.getParam()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (SkuAttrParam skuAttrParam : itemParam.getAttrValueList()) {
                stringBuffer.append(skuAttrParam.getSkuValue());
            }
            if (isExist.contains(stringBuffer.toString())) {
                throw new BusinessException("属性值不能相同"+stringBuffer.toString());
            }
            isExist.add(stringBuffer.toString());
        }
    }
}
