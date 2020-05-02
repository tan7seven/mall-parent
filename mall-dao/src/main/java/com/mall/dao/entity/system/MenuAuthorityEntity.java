package com.mall.dao.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("system_menu_Authority")
public class MenuAuthorityEntity extends BaseEntity {
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
