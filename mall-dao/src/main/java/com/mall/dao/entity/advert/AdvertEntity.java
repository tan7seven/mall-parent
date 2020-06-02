package com.mall.dao.entity.advert;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.common.enums.AdvertTypeEnum;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("mall_advert")
public class AdvertEntity extends BaseEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     * @see AdvertTypeEnum
     */
    private Integer type;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 跳转地址
     */
    private String skipUrl;
    /**
     * 背景颜色
     */
    private String backColor;
    /**
     * 排序
     */
    private Integer sort;
}
