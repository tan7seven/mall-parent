package com.mall.malladmin.entity.user;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址表
 */
@Data
@Entity
@Table(name = "mall_user_address")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class UserAdressEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(length = 32,name = "user_address_id")
    private String userAddressId;
    /**
     * 用户编号
     */
    @Column(length = 15,name = "user_id")
    private String userId;
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
    /**
     * 是否可用：0->是；1->否
     */
    @Column(length = 1,name = "is_usable")
    private String isUsable;
    /**
     * 默认收货地址 ：0->是；1->否
     */
    @Column(length = 1,name = "is_receive")
    private String isReceive;
    /**
     * 新增时间
     */
    @Column(name = "create_Time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "modify_Time")
    private Date modifyTime;

}
