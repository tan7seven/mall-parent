package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 公司收货地址表
 */
@Data
@TableName("company_address")
public class CompanyAddressEntity {
    /**
     * 主键
     */
    @Id
    private String addressId;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 默认发货地址：0->是；1->否
     */
    private Integer isSend;
    /**
     * 默认收货地址 ：0->是；1->否
     */
    private Integer isReceive;
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
