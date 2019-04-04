package com.mall.malladmin.service;

import com.mall.malladmin.dto.UserDto;
import com.mall.malladmin.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息service
 */
public interface UserService {
    /**
     * 新增
     * @param entity
     * @return
     */
    UserEntity add(UserEntity entity);

    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    List<UserEntity> getList(UserDto dto);

    /**
     *  根据用户ID查找
     * @param userId
     * @return
     */
    Optional<UserEntity> findById(String userId);
}
