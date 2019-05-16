package com.mall.malladmin.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
public class ProductImgVo implements Serializable {
    /**
     *商品图片编号
     */
    private Integer imgId;
    /**
     * 图片路径
     */
    private String imgUrl;
    /**
     * 图片名
     */
    private String name;
    /**
     * 	图片大小
     */
    /**
     * 新增时间
     */
    private Date addTime = new Date();
}
