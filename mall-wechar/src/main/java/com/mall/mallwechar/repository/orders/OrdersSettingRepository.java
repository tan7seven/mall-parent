package com.mall.mallwechar.repository.orders;

import com.mall.mallmodel.entity.orders.OrdersSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersSettingRepository extends JpaRepository<OrdersSettingEntity, Integer>, JpaSpecificationExecutor<OrdersSettingEntity> {
}
