package com.mall.malladmin.dto.orders;

import lombok.Data;

import java.util.Date;

/**
 * 订单操作历史日志表
 */
@Data
public class OrdersOperationLogDto {
    /**
     * 主键
     */
    private String operationId;
    /**
     * 订单编号
     */
    private String ordersId;
    /**
     * 操作人ID
     */
    private String operationPerson;
    /**
     * 操作人姓名
     */
    private String name;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private String ordersStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
}
