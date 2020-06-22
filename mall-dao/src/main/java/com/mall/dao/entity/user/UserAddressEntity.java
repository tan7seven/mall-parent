package com.mall.dao.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * 用户收货地址表
 */
@Data
@TableName("mall_user_address")
public class UserAddressEntity extends BaseEntity {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人电话
     */
    private String mobile;
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
    private String address;
    /**
     * 是否可用：0->是；1->否
     */
    @TableField(value = "is_usable")
    private Boolean usable;
    /**
     * 默认收货地址 ：0->否；1->是
     */

    @TableField(value = "is_defaulted")
    private Boolean defaulted;
}
