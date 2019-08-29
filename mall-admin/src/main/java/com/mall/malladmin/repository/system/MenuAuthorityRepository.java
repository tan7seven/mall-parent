package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.MenuAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuAuthorityRepository extends JpaRepository<MenuAuthorityEntity, String>, JpaSpecificationExecutor<MenuAuthorityEntity> {

    /**
     * 根据用户ID删除菜单权限
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 根据用户ID获取
     * @param userId
     * @return
     */
    List<MenuAuthorityEntity> findByUserId(String userId);
}
