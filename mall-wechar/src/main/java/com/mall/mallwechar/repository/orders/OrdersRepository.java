package com.mall.mallwechar.repository.orders;

import com.mall.mallmodel.entity.orders.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersRepository extends JpaRepository<OrdersEntity, String>, JpaSpecificationExecutor<OrdersEntity> {
}
