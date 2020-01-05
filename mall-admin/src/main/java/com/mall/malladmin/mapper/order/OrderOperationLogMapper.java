package com.mall.malladmin.mapper.order;

import com.mall.malladmin.dto.order.OrderOperationLogDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录
 */
public interface OrderOperationLogMapper {

    /**
     * 获取订单操作记录
     * @param dto
     * @return
     */
    List<OrderOperationLogDTO> getOperationLog(@Param("dto") OrderOperationLogDTO dto);
}
