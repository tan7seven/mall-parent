package com.mall.dao.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址表
 */
@Data
@TableName("mall_user_address")
public class UserAdressEntity extends BaseEntity {
    /**
     * 主键
     */
    private String userAddressId;
    /**
     * 用户编号
     */
    private String userId;
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
    /**
     * 是否可用：0->是；1->否
     */
    private Integer isUsable;
    /**
     * 默认收货地址 ：0->是；1->否
     */
    private Integer isReceive;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;

}
