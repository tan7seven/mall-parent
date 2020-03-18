package com.mall.dao.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.entity.order.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderEntity> {
    List<OrderDTO> findList(Page page ,@Param("dto") OrderDTO dto);

    /**
     * 获取订单跟订单明细
     * @param id
     * @return
     */
    OrderDTO findOrderAndItems(@Param("id") String id);
}
