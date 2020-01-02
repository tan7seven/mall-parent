package com.mall.malladmin.service.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import com.mall.malladmin.security.UserDetailsImpl;

import java.util.List;

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

    /**
     * 退货订单确认
     * @param dto
     */
    void updateApplyStatus(OrdersReturnApplyDto dto, UserDetailsImpl userDetails);

    /**
     * 批量删除退货申请单
     * @param ids
     */
    void deleteApply(List<String> ids);
}
