package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息（代码冲突测试github）
 */
@Data
@TableName("mall_product")
public class ProductEntity  implements Serializable {

    /**
     * 状态：0-上架
     */
    public static final Integer PUT_AWAY = 0;
    /**
     * 状态：1-下架
     */
    public static final Integer SOLD_OUT = 1;

    /**
     *商品编号
     */
    @Id
    private Integer productId;
    /**
     * 分类编号
     */
    private Integer typeId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品最低价
     */
    private BigDecimal priceMin;
    /**
     * 点击量
     */
    private Integer hits;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     */
    private Integer isPutaway;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    private Integer isUsable;
    /**
     * 是否删除
     * 0：正常
     * 1：已删除
     */
    private Integer isDelete;
}
