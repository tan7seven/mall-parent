package com.mall.malladmin.repository.order;

import com.mall.malladmin.entity.order.OrderSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderSettingRepository extends JpaRepository<OrderSettingEntity, Integer>, JpaSpecificationExecutor<OrderSettingEntity> {
}
