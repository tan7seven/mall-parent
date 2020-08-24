package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("system_button_authority")
public class ButtonAuthorityEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 按钮CODE
     */
    private String buttonCode;
    /**
     * 菜单主键
     */
    private Long menuId;
}
