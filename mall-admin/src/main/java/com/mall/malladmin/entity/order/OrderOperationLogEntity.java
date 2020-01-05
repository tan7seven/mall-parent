package com.mall.malladmin.entity.order;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单操作历史日志表
 */
@Data
@Entity
@Table(name = "mall_order_operation_log")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class OrderOperationLogEntity {
        /**
         * 主键
         */
        @Id
        @GeneratedValue(generator = "uuid2")
        @Column(length = 32,name = "operation_id")
        private String operationId;
        /**
         * 订单编号
         */
        @Column(length = 32,name = "order_id")
        private String orderId;
        /**
         * 操作人ID
         */
        @Column(length = 32,name = "operation_person")
        private String operationPerson;
        /**
         * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
         */
        @Column(name = "order_status", length = 1)
        private String orderStatus;
        /**
         * 备注
         */
        @Column(name = "remark")
        private String remark;
        /**
         * 创建时间
         */
        @Column(name = "create_time")
        private Date createTime;

}
