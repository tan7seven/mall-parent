package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.ButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ButtonRepository extends JpaRepository<ButtonEntity, String>, JpaSpecificationExecutor<ButtonEntity> {
}
