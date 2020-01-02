package com.mall.mallmodel.entity.user;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分表
 */
@Data
@Entity
@Table(name = "mall_user_score")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class UserScoreEntity implements Serializable {
    /**
     *UUID
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(length = 32,name = "user_score_id")
    private String userScoreId;
    /**
     * 用户ID
     */
    @Column(length = 15, name = "user_id")
    private String userId;
    /**
     * 	积分
     */
    @Column(name = "score")
    private Integer score;
    /**
     * 	积分来源 	1->订单、2->评价、3->订单取消返还、4->拒收返还
     */
    @Column(length = 1,name = "source")
    private String source;
    /**
     * 	积分来源ID
     */
    @Column(length = 64,name = "source_id")
    private String sourceId;
    /**
     * 	积分标识 0->收入、1->支出
     */
    @Column(length = 1,name = "score_type")
    private String scoreType;
    /**
     * 	创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
