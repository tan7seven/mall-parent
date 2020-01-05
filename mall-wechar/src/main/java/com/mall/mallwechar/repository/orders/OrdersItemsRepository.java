package com.mall.mallwechar.repository.order;

import com.mall.mallmodel.entity.order.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Integer>, JpaSpecificationExecutor<OrderItemsEntity> {
}
