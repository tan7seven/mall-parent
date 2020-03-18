package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 商品类目表
 */
@Data
@TableName("mall_product_type")
public class ProductTypeEntity  implements Serializable {

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
     * 等级
     */
    private Integer level;
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
}
