package com.mall.malladmin.repository.orders;

import com.mall.malladmin.entity.orders.OrdersSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersSettingRepository extends JpaRepository<OrdersSettingEntity, Integer>, JpaSpecificationExecutor<OrdersSettingEntity> {
}
