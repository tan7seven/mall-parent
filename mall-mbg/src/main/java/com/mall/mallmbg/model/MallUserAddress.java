package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallUserAddress implements Serializable {
    /**
     * UUID
     *
     * @mbggenerated
     */
    private String userAddressId;

    /**
     * 市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 	注册时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 	详细地址
     *
     * @mbggenerated
     */
    private String detailAddress;

    /**
     * 是否默认收货地址	 0:是，1：否
     *
     * @mbggenerated
     */
    private String isReceive;

    /**
     * 是否可用	0:可用，1：禁用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 更新时间	-
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 收货人姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 收货人电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 省、直辖市	
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 区
     *
     * @mbggenerated
     */
    private String region;

    /**
     * 用户ID
     *
     * @mbggenerated
     */
    private String userId;

    private static final long serialVersionUID = 1L;

    public String getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(String userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userAddressId=").append(userAddressId);
        sb.append(", city=").append(city);
        sb.append(", createTime=").append(createTime);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", isReceive=").append(isReceive);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", province=").append(province);
        sb.append(", region=").append(region);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}