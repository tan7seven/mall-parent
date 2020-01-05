package com.mall.mallwechar.repository.order;

import com.mall.mallmodel.entity.order.OrderOperationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderOperationLogRepository extends JpaRepository<OrderOperationLogEntity, Integer>, JpaSpecificationExecutor<OrderOperationLogEntity> {
}
