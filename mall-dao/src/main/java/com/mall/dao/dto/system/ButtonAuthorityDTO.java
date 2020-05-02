package com.mall.dao.dto.system;

import lombok.Data;

@Data
public class ButtonAuthorityDTO {
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

}