package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  商品属性名属性值关联表
 */
@Data
@TableName("mall_product_detail")
public class ProductDetailEntity implements Serializable{
    /**
     * 编号
     */
    @Id
    private Integer detailId;

    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 详情信息
     */
    private String detail;
}
