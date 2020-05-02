package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品信息
 */
@Data
public class ProductDTO extends CommonDTO implements Serializable {
    /**
     *商品编号
     */
    private Long id;
    /**
     * 分类编号
     */
    private Long typeId;
    /**
     * 点击量
     */
    private Integer hits;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品最低价
     */
    private BigDecimal minPrice;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     */
    private Boolean putaway;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 是否可用
     */
    private Boolean usable;
}
