package com.mall.mallmodel.entity.orders;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单操作历史日志表
 */
@Data
@Entity
@Table(name = "mall_orders_operation_log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrdersOperationLogEntity {
        /**
         * 主键
         */
        @Id
        @GeneratedValue(generator = "jpa-uuid")
        @Column(length = 32,name = "operation_id")
        private String operationId;
        /**
         * 订单编号
         */
        @Column(length = 32,name = "orders_id")
        private String ordersId;
        /**
         * 操作人ID
         */
        @Column(length = 32,name = "operation_person")
        private String operationPerson;
        /**
         * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
         */
        @Column(name = "orders_status", length = 1)
        private String ordersStatus;
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
