package com.mall.mallmodel.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
@Entity
@Table(name = "mall_product_img")
public class ProductImgEntity  implements Serializable {
    /**
     *商品图片编号
     */
    @Id
    @Column(name = "img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imgId;
    /**
     * 图片路径
     */
    @Column(name = "img_url")
    private String imgUrl;
    /**
     * 图片名
     */
    @Column(length = 64)
    private String name;
    /**
     * 	图片大小
     */
    @Column()
    private Integer size;
    /**
     * 新增时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
