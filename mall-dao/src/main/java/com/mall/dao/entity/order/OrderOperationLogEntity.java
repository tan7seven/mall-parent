package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 订单操作历史日志表
 */
@Data
@TableName("mall_order_operation_log")
public class OrderOperationLogEntity extends BaseEntity {
        /**
         * 订单编号
         */
        private Long orderId;
        /**
         * 操作人ID
         */
        private Long operationPerson;
        /**
         * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
         */
        private Integer orderStatus;
        /**
         * 备注
         */
        private String remark;
}
