package com.mall.malladmin.vo;

import lombok.Data;

@Data
public class CompanyAddressVo {
    /**
     * 主键
     */
    private String addressId;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 默认发货地址：0->是；1->否
     */
    private String isSend;
    /**
     * 默认发货地址 ：0->是；1->否
     */
    private String isReceive;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 省、直辖市
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址
     */
    private String detailAddress;
}