package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
@TableName("mall_product_property_name")
public class ProductPropertyNameEntity  implements Serializable {
    public static final String IS_SALE = "0";
    public static final String NOT_SALE = "1";
    /**
     * 编号
     */
    @Id
    private Integer propertyNameId;
    /**
     * 商品类目ID
     */
    private Integer typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 	是否销售属性
     * 	0：否
     * 	1：是
     */
    private Integer isSale;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    private Integer isShow;
    /**
     * 	是否删除
     * 	0：正常
     * 	1：已删除
     */
    private Integer isDelete;

}
