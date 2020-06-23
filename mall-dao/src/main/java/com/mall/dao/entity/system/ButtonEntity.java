package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 系统按钮
 */
@Data
@TableName("system_button")
public class ButtonEntity extends BaseEntity {
    /**
     * 主键
     */
    private Long buttonId;
    /**
     * 菜单主键
     */
    private Long menuId;
    /**
     * 按钮编码
     */
    private String buttonCode;
    /**
     * 按钮名称
     */
    private  String buttonName;
}
