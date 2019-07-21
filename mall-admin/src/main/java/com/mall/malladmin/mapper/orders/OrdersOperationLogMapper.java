package com.mall.malladmin.mapper.orders;

import com.mall.malladmin.dto.orders.OrdersOperationLogDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录
 */
public interface OrdersOperationLogMapper {

    /**
     * 获取订单操作记录
     * @param dto
     * @return
     */
    List<OrdersOperationLogDto> getOperationLog(@Param("dto") OrdersOperationLogDto dto);
}
