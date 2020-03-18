package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存表
 */
@Data
@TableName("mall_product_sku")
public class ProductSkuEntity  implements Serializable {

    /**
     * 编号
     */
    @Id
    private Integer skuId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 属性值
     */
    private String properties;
    /**
     * 	商品价格
     */
    private BigDecimal price;
    /**
     * 	图片地址
     */
    private String picUrl;
    /**
     * 	商品成本
     */
    private BigDecimal cost;

    private Integer sellSum;
    /**
     * 	商品库存
     */
    private Integer stock;
    /**
     * 新增时间
     */
    private Date createTime = new Date();
    /**
     * 更新时间
     */
    private Date modifyTime = new Date();
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    private Integer isDelete;
}
