package com.mall.malladmin.repository.orders;

import com.mall.malladmin.entity.orders.OrdersItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersItemsRepository extends JpaRepository<OrdersItemsEntity, Integer>, JpaSpecificationExecutor<OrdersItemsEntity> {
}
