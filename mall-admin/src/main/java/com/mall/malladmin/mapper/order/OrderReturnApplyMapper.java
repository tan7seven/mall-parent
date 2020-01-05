package com.mall.malladmin.mapper.order;

import com.mall.malladmin.dto.order.OrderReturnApplyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请
 */
public interface OrderReturnApplyMapper {

    /**
     * 查询
     * @param dto
     * @return
     */
    List<OrderReturnApplyDTO> findList(@Param("dto") OrderReturnApplyDTO dto);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    OrderReturnApplyDTO findById(@Param("id") String id);
}
