package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/10
 */
@Data
@TableName("mall_product_attr_type")
public class ProductAttrTypeEntity extends BaseEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 显示参数数量
     */
    private Integer paramNum;
    /**
     * 销售规格数量
     */
    private Integer attrNum;
}
