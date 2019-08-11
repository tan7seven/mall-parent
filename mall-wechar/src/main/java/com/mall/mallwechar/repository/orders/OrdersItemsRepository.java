package com.mall.mallwechar.repository.orders;

import com.mall.mallmodel.entity.orders.OrdersItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersItemsRepository extends JpaRepository<OrdersItemsEntity, Integer>, JpaSpecificationExecutor<OrdersItemsEntity> {
}
