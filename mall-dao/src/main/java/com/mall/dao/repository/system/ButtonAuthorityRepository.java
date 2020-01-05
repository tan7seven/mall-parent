package com.mall.dao.repository.system;

import com.mall.dao.entity.system.ButtonAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ButtonAuthorityRepository  extends JpaRepository<ButtonAuthorityEntity, String>, JpaSpecificationExecutor<ButtonAuthorityEntity> {

    /**
     * 根据菜单编码和用户编号删除
     * @param menuId
     */
    void deleteByMenuIdAndUserId(String menuId, String userId);

    /**
     * 根据按钮ID删除
     * @param buttonId
     */
    void deleteByButtonId(String buttonId);
}
