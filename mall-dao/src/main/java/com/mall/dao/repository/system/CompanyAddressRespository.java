package com.mall.dao.repository.system;

import com.mall.dao.entity.order.CompanyAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyAddressRespository  extends JpaRepository<CompanyAddressEntity, String>, JpaSpecificationExecutor<CompanyAddressEntity> {
}
