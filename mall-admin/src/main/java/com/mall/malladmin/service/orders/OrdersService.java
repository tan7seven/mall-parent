package com.mall.malladmin.service.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.security.UserDetailsImpl;

/**
 * 订单信息
 */
public interface OrdersService {
    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageInfo<OrdersDto> getPage(OrdersDto dto);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    OrdersDto getOrdersById(String id);

    /**
     * 修改订单收货信息
     * @param dto
     */
    void updateReceiverInfo(OrdersDto dto, UserDetailsImpl userDetails);
}
