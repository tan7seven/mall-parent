package com.mall.malladmin.repository.orders;

import com.mall.malladmin.entity.orders.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>, JpaSpecificationExecutor<OrdersEntity> {
}
