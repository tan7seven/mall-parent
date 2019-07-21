package com.mall.malladmin.mapper.orders;

import com.mall.malladmin.dto.orders.OrdersDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {
    List<OrdersDto> findList(@Param("dto") OrdersDto dto);

    /**
     * 获取订单跟订单明细
     * @param id
     * @return
     */
    OrdersDto findOrdersAndItems(@Param("id") String id);
}
