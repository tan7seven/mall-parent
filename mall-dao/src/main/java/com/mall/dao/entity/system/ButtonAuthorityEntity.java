package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("system_button_authority")
public class ButtonAuthorityEntity extends BaseEntity {
    /**
     * 主键
     */
    private String buttonAuthorityId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 按钮主键
     */
    private String buttonId;
    /**
     * 菜单主键
     */
    private String menuId;
}
