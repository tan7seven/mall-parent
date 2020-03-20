package com.mall.dao.entity.user;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分表
 */
@Data
@TableName("mall_user_score")
public class UserScoreEntity implements Serializable {
    /**
     *UUID
     */
    private String userScoreId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 	积分
     */
    private Integer score;
    /**
     * 	积分来源 	1->订单、2->评价、3->订单取消返还、4->拒收返还
     */
    private Integer source;
    /**
     * 	积分来源ID
     */
    private String sourceId;
    /**
     * 	积分标识 0->收入、1->支出
     */
    private Integer scoreType;
    /**
     * 	创建时间
     */
    private Date createTime;
}
