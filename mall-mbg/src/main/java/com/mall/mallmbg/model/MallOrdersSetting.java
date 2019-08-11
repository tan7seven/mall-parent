package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallOrdersSetting implements Serializable {
    private Integer settingId;

    /**
     * 正常订单超时时间(分)
     *
     * @mbggenerated
     */
    private Integer normalOrdersOvertime;

    /**
     * 发货后自动确认收货时间（天）
     *
     * @mbggenerated
     */
    private Integer confirmOvertime;

    /**
     * 自动完成交易时间，不能申请售后（天）
     *
     * @mbggenerated
     */
    private Integer finishOvertime;

    /**
     * 订单完成后自动好评时间（天）
     *
     * @mbggenerated
     */
    private Integer commentOvertime;

    private static final long serialVersionUID = 1L;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getNormalOrdersOvertime() {
        return normalOrdersOvertime;
    }

    public void setNormalOrdersOvertime(Integer normalOrdersOvertime) {
        this.normalOrdersOvertime = normalOrdersOvertime;
    }

    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }

    public Integer getFinishOvertime() {
        return finishOvertime;
    }

    public void setFinishOvertime(Integer finishOvertime) {
        this.finishOvertime = finishOvertime;
    }

    public Integer getCommentOvertime() {
        return commentOvertime;
    }

    public void setCommentOvertime(Integer commentOvertime) {
        this.commentOvertime = commentOvertime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", settingId=").append(settingId);
        sb.append(", normalOrdersOvertime=").append(normalOrdersOvertime);
        sb.append(", confirmOvertime=").append(confirmOvertime);
        sb.append(", finishOvertime=").append(finishOvertime);
        sb.append(", commentOvertime=").append(commentOvertime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}