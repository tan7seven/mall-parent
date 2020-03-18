package com.mall.dao.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("system_menu_Authority")
public class MenuAuthorityEntity {
    /**
     * 主键
     */
    @Id
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
