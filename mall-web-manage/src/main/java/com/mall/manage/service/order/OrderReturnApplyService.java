package com.mall.manage.service.order;

import com.github.pagehelper.PageInfo;
import com.mall.dao.dto.order.OrderReturnApplyDTO;
import com.mall.manage.security.UserDetailsImpl;

import java.util.List;

/**
 * 订单退货申请
 */
public interface OrderReturnApplyService {

    /**
     * 根据ID获取明细
     * @param id
     * @return
     */
    OrderReturnApplyDTO findById(String id);
    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageInfo<OrderReturnApplyDTO> getPage(OrderReturnApplyDTO dto);

    /**
     * 退货订单确认
     * @param dto
     */
    void updateApplyStatus(OrderReturnApplyDTO dto, UserDetailsImpl userDetails);

    /**
     * 批量删除退货申请单
     * @param ids
     */
    void deleteApply(List<String> ids);
}
