package com.mall.malladmin.service.system;

import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息service
 */
public interface AdminService {

    /**
     * 重新加载用户信息
     * @param userDetails
     * @return
     */
    UserDetailsImpl adminLogin(String username);
    /**
     * 新增
     * @param dto
     * @return
     */
    AdminEntity add(AdminDto dto);
    /**
     * 修改
     * @param dto
     * @return
     */


    void update(AdminDto dto, String id);
    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    List<AdminEntity> getList(AdminDto dto);
    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    Page<AdminEntity> getPage(AdminDto dto);
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

    /**
     * 删除（逻辑删除）
     * @param ids
     */
    void deleteAdmin(String[] ids);

    /**
     * 是否可用
     * @param dto
     */
    void updateIsUsable(AdminDto dto);

    /**
     * 菜单授权
     * @param dto
     */
    @Transactional
    void menuAuthority(AdminDto dto);


    /**
     * 菜单授权
     * @param dto
     */
    @Transactional
    void buttonAuthority(AdminDto dto);
    /**
     * 根据用户ID获取已授权菜单列表ID
     * @param userId
     * @return
     */
    @Transactional
    List<String> getAdminMenuAuthority(String userId);

}
