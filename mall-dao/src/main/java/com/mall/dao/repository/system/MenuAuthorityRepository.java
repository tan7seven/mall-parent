package com.mall.dao.repository.system;

import com.mall.dao.entity.system.MenuAuthorityEntity;
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
     * 根据菜单ID删除
     * @param menuId
     */
    void deleteByMenuId(String menuId);

    /**
     * 根据用户ID获取
     * @param userId
     * @return
     */
    List<MenuAuthorityEntity> findByUserId(String userId);
}
