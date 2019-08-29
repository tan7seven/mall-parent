package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallUserScore implements Serializable {
    /**
     * UUID
     *
     * @mbggenerated
     */
    private String userScoreId;

    /**
     * 	创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 积分
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * 	积分标识  	0->收入、1->支出
     *
     * @mbggenerated
     */
    private String scoreType;

    /**
     * 积分来源 1->订单、2->评价、3->订单取消返还、4->拒收返还
     *
     * @mbggenerated
     */
    private String source;

    /**
     * 积分来源ID
     *
     * @mbggenerated
     */
    private String sourceId;

    /**
     * 用户ID
     *
     * @mbggenerated
     */
    private String userId;

    private static final long serialVersionUID = 1L;

    public String getUserScoreId() {
        return userScoreId;
    }

    public void setUserScoreId(String userScoreId) {
        this.userScoreId = userScoreId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userScoreId=").append(userScoreId);
        sb.append(", createTime=").append(createTime);
        sb.append(", score=").append(score);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", source=").append(source);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}