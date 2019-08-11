package com.mall.mallmodel.entity.system;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "system_Button_Authority")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ButtonAuthorityEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "button_Authority_Id")
    private String buttonAuthorityId;
    /**
     * 用户ID
     */
    @Column(length = 32,name = "user_Id")
    private String userId;
    /**
     * 按钮主键
     */
    @Column(length = 32,name = "button_Id")
    private String buttonId;

}
