package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<MenuEntity, String>, JpaSpecificationExecutor<MenuEntity> {
}
