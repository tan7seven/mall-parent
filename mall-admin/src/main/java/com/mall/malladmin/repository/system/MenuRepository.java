package com.mall.malladmin.repository.system;

import com.mall.malladmin.entity.system.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, String>, JpaSpecificationExecutor<MenuEntity> {

    /**
     * 父级编码删除
     * @param parentId
     */
    void deleteByParentId(String parentId);

    /**
     * 根据父级编码查询
     * @param parentId
     * @return
     */
    List<MenuEntity> findByParentId(String parentId);

}
