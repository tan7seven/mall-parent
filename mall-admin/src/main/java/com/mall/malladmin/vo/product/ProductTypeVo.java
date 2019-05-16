package com.mall.malladmin.vo.product;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;


/**
 * 商品类目表
 */
@Data
public class ProductTypeVo implements Serializable {

    /**
     * 编号
     */
    @Id
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
    private String status;
}
