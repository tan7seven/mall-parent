package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallProductImg implements Serializable {
    /**
     * 商品图片编号
     *
     * @mbggenerated
     */
    private Integer imgId;

    /**
     * 商品编号
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 图片路径
     *
     * @mbggenerated
     */
    private String imgUrl;

    /**
     * 新增时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 图片名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 图片大小
     *
     * @mbggenerated
     */
    private Integer size;

    private static final long serialVersionUID = 1L;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", imgId=").append(imgId);
        sb.append(", productId=").append(productId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", name=").append(name);
        sb.append(", size=").append(size);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}