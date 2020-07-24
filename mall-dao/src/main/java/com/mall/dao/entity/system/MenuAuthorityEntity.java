package com.mall.dao.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("system_menu_authority")
public class MenuAuthorityEntity extends BaseEntity {

    /**
     * 按钮主键
     */
    private Long menuId;
    /**
     * 用户ID
     */
    private Long userId;
}
