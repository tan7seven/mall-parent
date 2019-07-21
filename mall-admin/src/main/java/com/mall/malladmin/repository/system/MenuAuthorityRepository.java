package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.MenuAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuAuthorityRepository extends JpaRepository<MenuAuthorityEntity, String>, JpaSpecificationExecutor<MenuAuthorityEntity> {
}
