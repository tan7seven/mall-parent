package com.mall.malladmin.repository.orders;

import com.mall.malladmin.entity.orders.OrdersOperationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersOperationLogRepository extends JpaRepository<OrdersOperationLogEntity, Integer>, JpaSpecificationExecutor<OrdersOperationLogEntity> {
}
