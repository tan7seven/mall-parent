package com.mall.dao.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.order.OrderOperationLogDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录
 */
public interface OrderOperationLogMapper extends BaseMapper<OrderOperationLogEntity> {

    /**
     * 获取订单操作记录
     */
    List<OrderOperationLogDTO> getOperationLog(@Param("orderId") Long orderId);
}
