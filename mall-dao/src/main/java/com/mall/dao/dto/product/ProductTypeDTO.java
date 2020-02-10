package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;


/**
 * 商品类目表
 */
@Data
public class ProductTypeDTO extends CommonDTO implements Serializable {

    /**
     * 编号
     */
    private Integer typeId;
    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 父类目编号
     */
    private Integer parentId;
    /**
     * 	排序
     */
    private Integer sort;
    /**
     * 是否显示在导航栏
     */
    private Integer isNavigationBar;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    private Integer isUsable;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    private Integer isDelete;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 类目销售属性名
     */
    private String[] propertyNameCheckedIsSale;
    /**
     * 类目显示参数名
     */
    private String[] propertyNameCheckedNotSale;
}

