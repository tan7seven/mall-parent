package com.mall.malladmin.mapper.orders;

import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请
 */
public interface OrdersReturnApplyMapper {

    /**
     * 查询
     * @param dto
     * @return
     */
    List<OrdersReturnApplyDto> findList(@Param("dto") OrdersReturnApplyDto dto);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    OrdersReturnApplyDto findById(@Param("id") String id);
}
