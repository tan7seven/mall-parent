package com.mall.mallwechar.repository.system;

import com.mall.mallmodel.entity.system.ButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ButtonRepository extends JpaRepository<ButtonEntity, String>, JpaSpecificationExecutor<ButtonEntity> {
}
