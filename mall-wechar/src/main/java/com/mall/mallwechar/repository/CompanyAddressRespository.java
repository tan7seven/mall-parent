package com.mall.mallwechar.repository;

import com.mall.mallmodel.entity.CompanyAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyAddressRespository  extends JpaRepository<CompanyAddressEntity, String>, JpaSpecificationExecutor<CompanyAddressEntity> {
}
