package com.mall.malladmin.vo.product;

import com.mall.malladmin.vo.CommonVo;
import lombok.Data;

import java.io.Serializable;


/**
 * 商品类目表
 */
@Data
public class ProductTypeVo extends CommonVo implements Serializable {

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
    private String isNavigationBar;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    private String status;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 类目属性名
     */
    private String[] propertyNameChecked;
}
