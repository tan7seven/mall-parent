package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.ButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ButtonRepository extends JpaRepository<ButtonEntity, String>, JpaSpecificationExecutor<ButtonEntity> {

    /**
     * 根据菜单ID删除
     * @param menuId
     */
    void deleteByMenuId(String menuId);

    /**
     * 根据菜单ID获取
     * @param menuId
     * @return
     */
    List<ButtonEntity> findByMenuId(String menuId);
}
