package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户信息JPA
 */
public interface AdminRepository extends JpaRepository<AdminEntity, String>, JpaSpecificationExecutor<AdminEntity> {
}
