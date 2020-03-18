package com.mall.dao.entity.system;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ButtonAuthorityEntity {
    /**
     * 主键
     */
    @Id
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
