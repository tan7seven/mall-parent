package com.mall.mallwechar.repository.system;

import com.mall.mallmodel.entity.system.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<MenuEntity, String>, JpaSpecificationExecutor<MenuEntity> {
}
