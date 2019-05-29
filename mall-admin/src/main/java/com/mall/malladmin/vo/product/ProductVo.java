package com.mall.malladmin.vo.product;

import com.mall.malladmin.vo.common.CommonVo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 */
@Data
public class ProductVo extends CommonVo implements Serializable {
    /**
     *商品编号
     */
    @NotBlank(message = "商品编号不能为空！")
    private Integer productId;
    /**
     * 分类编号
     */
    @NotBlank(message = "分类编号不能为空！")
    private Integer typeId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品主图
     */
    private String imgUrl;
    /**
     * 商品最低价
     */
    private BigDecimal priceMin;
    /**
     * 点击量
     */
    private Integer hits;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     * 2：已删除
     */
    private String status;
    /**
     * 新增时间
     */
    private Date addTime;

}
