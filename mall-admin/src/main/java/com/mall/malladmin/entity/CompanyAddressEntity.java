package com.mall.malladmin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 公司收货地址表
 */
@Data
@Entity
@Table(name = "company_address")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class CompanyAddressEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(length = 32,name = "address_id")
    private String addressId;
    /**
     * 地址名称
     */
    @Column(length = 128,name = "address_name")
    private String addressName;
    /**
     * 默认发货地址：0->是；1->否
     */
    @Column(length = 1,name = "is_send")
    private String isSend;
    /**
     * 默认收货地址 ：0->是；1->否
     */
    @Column(length = 1,name = "is_receive")
    private String isReceive;
    /**
     * 收货人姓名
     */
    @Column(length = 64,name = "name")
    private String name;
    /**
     * 收货人电话
     */
    @Column(length = 64,name = "phone")
    private String phone;
    /**
     * 省、直辖市
     */
    @Column(length = 64,name = "province")
    private String province;
    /**
     * 市
     */
    @Column(length = 64,name = "city")
    private String city;
    /**
     * 区
     */
    @Column(length = 64,name = "region")
    private String region;
    /**
     * 详细地址
     */
    @Column(length = 128,name = "detail_address")
    private String detailAddress;
}
