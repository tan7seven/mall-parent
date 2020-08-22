package com.mall.manage.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/10
 */
public class AttrUtil {

    public static String getAttrStr(String attrJSON){
        try {
            List<ProductAttrValueVO> attrValueVOList = JSONObject.parseArray(attrJSON, ProductAttrValueVO.class);
            /** SKU属性*/
            StringBuffer attrVal = new StringBuffer();
            for (ProductAttrValueVO valueVO : attrValueVOList) {
                attrVal.append(valueVO.getSkuName() + " : ");
                attrVal.append(valueVO.getSkuValue() + "  ");
            }
            return attrVal.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
