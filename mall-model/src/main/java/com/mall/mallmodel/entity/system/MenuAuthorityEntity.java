package com.mall.mallmodel.entity.system;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "system_menu_Authority")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MenuAuthorityEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "menu_Authority_Id")
    private String menuAuthorityId;
    /**
     * 按钮主键
     */
    @Column(length = 32,name = "menu_Id")
    private String menuId;
    /**
     * 用户ID
     */
    @Column(length = 32,name = "user_Id")
    private String userId;
}
