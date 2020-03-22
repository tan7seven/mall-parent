package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息service
 */
public interface AdminService extends IService<AdminEntity> {

    /**
     * 重新加载用户信息
     * @return
     */
    UserDetailsImpl adminLogin(String username);
    /**
     * 新增
     * @param dto
     * @return
     */
    AdminEntity add(AdminDTO dto);
    /**
     * 修改
     * @param dto
     * @return
     */


    void update(AdminDTO dto, String id);
    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    List<AdminEntity> getList(AdminDTO dto);
    /**
     * 获取用户信息
     * @param dto
     * @return
     */
    Page<AdminEntity> getPage(AdminDTO dto);
    /**
     * 根据登录账号获取用户信息
     * @param loginCode
     * @return
     */
    AdminDTO findByLoginId(String loginCode);

    /**
     * 删除（逻辑删除）
     * @param ids
     */
    void deleteAdmin(List<String> ids);

    /**
     * 是否可用
     * @param dto
     */
    void updateIsUsable(AdminDTO dto);

    /**
     * 菜单授权
     * @param dto
     */
    @Transactional
    void menuAuthority(AdminDTO dto);


    /**
     * 菜单授权
     * @param dto
     */
    @Transactional
    void buttonAuthority(AdminDTO dto);
    /**
     * 根据用户ID获取已授权菜单列表ID
     * @param userId
     * @return
     */
    @Transactional
    List<String> getAdminMenuAuthority(String userId);

}
