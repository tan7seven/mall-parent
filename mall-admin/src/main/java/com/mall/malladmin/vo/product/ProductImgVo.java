package com.mall.malladmin.vo.product;

import com.mall.malladmin.vo.common.CommonVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
public class ProductImgVo extends CommonVo implements Serializable {

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
    private Date createTime;
}
