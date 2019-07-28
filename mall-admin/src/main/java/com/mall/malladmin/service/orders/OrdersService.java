package com.mall.malladmin.service.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * 修改订单价格
     * @param dto
     */
    void updateMoneyInfo(OrdersDto dto, UserDetailsImpl userDetails);

    /**
     * 修改订单备注
     * @param dto
     * @param userDetails
     */
    void updateRemarkInfo(OrdersDto dto, UserDetailsImpl userDetails);

    /**
     * 关闭订单
     * @param dto
     * @param userDetails
     */
    void closeOrders(OrdersDto dto, UserDetailsImpl userDetails);

    /**
     * 删除订单
     * @param ids
     * @param userDetails
     */
    @Transactional
    void deleteOrders(String[] ids, UserDetailsImpl userDetails);
}
