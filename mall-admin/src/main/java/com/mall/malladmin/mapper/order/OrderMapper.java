package com.mall.malladmin.mapper.order;

import com.mall.malladmin.dto.order.OrderDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    List<OrderDTO> findList(@Param("dto") OrderDTO dto);

    /**
     * 获取订单跟订单明细
     * @param id
     * @return
     */
    OrderDTO findOrderAndItems(@Param("id") String id);
}
