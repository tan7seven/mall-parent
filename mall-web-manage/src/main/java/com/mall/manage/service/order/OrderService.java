package com.mall.manage.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.manage.model.vo.order.OrderDetailVO;
import com.mall.manage.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单信息
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 获取订单明细
     * @param orderId
     * @return
     */
    OrderDetailVO getOrderDetail(Long orderId);
    /**
     * 修改订单价格
     * @param dto
     */
    void updateMoneyInfo(OrderDTO dto, UserDetailsImpl userDetails);

    /**
     * 关闭订单
     * @param dto
     * @param userDetails
     */
    void closeOrder(OrderDTO dto, UserDetailsImpl userDetails);
    /**
     * 关闭订单
     */
    void closeOrderList(List<String> ids, String remark, UserDetailsImpl userDetails);
    /**
     * 删除订单
     * @param ids
     * @param userDetails
     */
    @Transactional
    void deleteOrder(List<String> ids, UserDetailsImpl userDetails);

    /**
     * 订单发货
     * @param dtoList
     */
    @Transactional
    void deliveryOrder(List<OrderDTO> dtoList, UserDetailsImpl userDetails);
}
