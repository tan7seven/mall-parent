package com.mall.malladmin.service.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;

/**
 * 订单退货申请
 */
public interface OrdersReturnApplyService {

    /**
     * 根据ID获取明细
     * @param id
     * @return
     */
    OrdersReturnApplyDto findById(String id);
    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageInfo<OrdersReturnApplyDto> getPage(OrdersReturnApplyDto dto);
}
