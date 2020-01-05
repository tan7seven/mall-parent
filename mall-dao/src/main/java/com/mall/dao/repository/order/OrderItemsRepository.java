package com.mall.dao.repository.order;

import com.mall.dao.entity.order.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Integer>, JpaSpecificationExecutor<OrderItemsEntity> {
}
