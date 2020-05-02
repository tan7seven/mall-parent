package com.mall.dao.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.order.OrderReturnApplyDTO;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.entity.order.OrderReturnApplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请
 */
public interface OrderReturnApplyMapper extends BaseMapper<OrderReturnApplyEntity> {

    /**
     * 查询
     * @param dto
     * @return
     */
    List<OrderReturnApplyDTO> findList(Page page,  @Param("dto") OrderReturnApplyDTO dto);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    OrderReturnApplyDTO getById(@Param("id") String id);
}
