package com.mall.malladmin.repository.order;

import com.mall.malladmin.entity.order.OrderReturnApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderReturnApplyRepository extends JpaRepository<OrderReturnApplyEntity, String>, JpaSpecificationExecutor<OrderReturnApplyEntity> {
}
