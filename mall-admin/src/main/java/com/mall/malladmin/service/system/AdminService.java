package com.mall.malladmin.service.system;

import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.entity.system.AdminEntity;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息service
 */
public interface AdminService {
    /**
     * 新增
     * @param entity
     * @return
     */
    AdminEntity add(AdminEntity entity);

    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    List<AdminEntity> getList(AdminDto dto);

    /**
     *  根据用户ID查找
     * @param userId
     * @return
     */
    Optional<AdminEntity> findById(String userId);

    /**
     * 根据登录账号获取用户信息
     * @param loginCode
     * @return
     */
    AdminDto findByLoginId(String loginCode);
}
