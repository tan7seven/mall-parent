package com.mall.dao.dto.system;


import lombok.Data;

@Data
public class MenuAuthorityDTO {
    /**
     * 主键
     */
    private String menuAuthorityId;
    /**
     * 按钮主键
     */
    private String menuId;
    /**
     * 用户ID
     */
    private String userId;
}
