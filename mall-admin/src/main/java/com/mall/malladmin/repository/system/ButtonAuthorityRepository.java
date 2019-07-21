package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.ButtonAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ButtonAuthorityRepository  extends JpaRepository<ButtonAuthorityEntity, String>, JpaSpecificationExecutor<ButtonAuthorityEntity> {
}
