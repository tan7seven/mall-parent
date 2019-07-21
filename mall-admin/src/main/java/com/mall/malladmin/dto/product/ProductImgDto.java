package com.mall.malladmin.dto.product;

import com.mall.malladmin.dto.common.CommonDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
public class ProductImgDto extends CommonDto implements Serializable {

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
