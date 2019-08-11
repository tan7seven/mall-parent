package com.mall.mallwechar.repository.orders;

import com.mall.mallmodel.entity.orders.OrdersReturnApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersReturnApplyRepository extends JpaRepository<OrdersReturnApplyEntity, String>, JpaSpecificationExecutor<OrdersReturnApplyEntity> {
}
