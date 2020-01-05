package com.mall.malladmin.service.system.impl;

import com.mall.malladmin.constant.CommonConstant;
import com.mall.malladmin.dto.system.AdminDTO;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.entity.system.ButtonAuthorityEntity;
import com.mall.malladmin.entity.system.MenuAuthorityEntity;
import com.mall.malladmin.entity.system.MenuEntity;
import com.mall.malladmin.enumUtil.AdminRoleEnum;
import com.mall.malladmin.mapper.system.AdminMapper;
import com.mall.malladmin.mapper.system.ButtonMapper;
import com.mall.malladmin.repository.system.AdminRepository;
import com.mall.malladmin.repository.system.ButtonAuthorityRepository;
import com.mall.malladmin.repository.system.MenuAuthorityRepository;
import com.mall.malladmin.repository.system.MenuRepository;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.system.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ButtonMapper buttonMapper;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuAuthorityRepository menuAuthorityRepository;

    @Autowired
    private ButtonAuthorityRepository buttonAuthorityRepository;

//    @Autowired
//    private RedisUtil redisUtil;

    @Override
    public UserDetailsImpl adminLogin(String username) {
        UserDetailsImpl user = new UserDetailsImpl();
            /*String redisUser = (String) redisUtil.get(username);
            log.info("redis获取用户信息{}", redisUser);
            if(null != redisUser){
                JSONObject resultJson = JSONObject.parseObject(redisUser);
                user.setRole((String) resultJson.get("role"));
                JSONArray menuList = resultJson.getJSONArray("menuList");
                menuList = null == menuList? new JSONArray():menuList;
                user.setMenuList(menuList.toJavaList(String.class));
                user.setIcon((String) resultJson.get("icon"));
                user.setUserId((String) resultJson.get("userId"));
                user.setPassword((String) resultJson.get("password"));
                user.setUsername((String) resultJson.get("username"));
                JSONArray buttonList = resultJson.getJSONArray("buttonList");
                buttonList = null == buttonList? new JSONArray():buttonList;
                user.setButtonList(buttonList.toJavaList(String.class));
                return user;
            }*/
        AdminDTO dto  = this.findByLoginId(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(dto.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setUserId(dto.getUserId());
        user.setIcon(dto.getPicUrl());
        List<String> buttonList = new ArrayList<>();
        //获取用户角色
        user.setRole(AdminRoleEnum.getValue(dto.getRole()));
        //获取用户权限-按钮
        List<String> buttonCodeList = adminMapper.getButtonCodeAuthority(dto);
        buttonList.addAll(buttonCodeList);
        user.setButtonList(buttonList);
        //获取用户权限-菜单
        List<String> menuCodeList = adminMapper.getMenuCodeListAuthority(dto);
        user.setMenuList(menuCodeList);
//            redisUtil.set(username, JSONObject.toJSONString(user), 30000);
        return user;
    }

    @Override
    public AdminEntity add(AdminDTO dto) {
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreateTime(new Date());
        entity.setIsUsable(StringUtils.isNotBlank(entity.getIsUsable())?entity.getIsUsable():CommonConstant.IS_USABLE);
        entity.setModifyTime(new Date());
        entity.setRole(AdminEntity.ROLE_USER);
        entity.setPassword(AdminEntity.DEFAULT_PASSWORD);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        return adminRepository.save(entity);
    }

    @Override
    public void update(AdminDTO dto, String id) {
        AdminEntity entity = adminRepository.findById(id).get();
        entity.setLoginCode(dto.getLoginCode());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setPicUrl(dto.getPicUrl());
        entity.setIsUsable(dto.getIsUsable());
        entity.setModifyTime(new Date());
        adminRepository.save(entity);
    }

    @Override
    public List<AdminEntity> getList(AdminDTO dto) {
        List<AdminEntity>entities = adminRepository.findAll((Specification<AdminEntity>) (root, query, criteriaBuilder)->{
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(dto.getRole())){
                list.add(criteriaBuilder.like(root.get("role").as(String.class), "%"+dto.getRole()+"%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        });
        return entities;
    }

    @Override
    public Page<AdminEntity> getPage(AdminDTO dto) {
        Sort sort = new Sort(Sort.Direction.ASC, "modifyTime");
        Pageable page = PageRequest.of(dto.getPageNum()-1, dto.getPageSize(), sort);
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        Example<AdminEntity> example = Example.of(entity);
        Page<AdminEntity> result = adminRepository.findAll(example, page);
        return result;
    }

    @Override
    public Optional<AdminEntity> findById(String userId) {
        return adminRepository.findById(userId);
    }

    @Override
    public AdminDTO findByLoginId(String loginCode) {
        return adminMapper.findByLoginId(loginCode);
    }

    @Override
    public void deleteAdmin(List<String> ids) {
        for (String id : ids) {
            AdminEntity entity = adminRepository.findById(id).get();
            entity.setIsDelete(CommonConstant.IS_DELETE);
            adminRepository.save(entity);
        }
    }

    @Override
    public void updateIsUsable(AdminDTO dto) {
        AdminEntity entity = adminRepository.findById(dto.getUserId()).get();
        entity.setIsUsable(StringUtils.isBlank(dto.getIsUsable())?CommonConstant.IS_USABLE:dto.getIsUsable());
        adminRepository.save(entity);
    }

    @Override
    public void menuAuthority(AdminDTO dto) {
        menuAuthorityRepository.deleteByUserId(dto.getUserId());
        if (dto.getMenuList().isEmpty()) {
            return;
        }
        dto.getMenuList().forEach(s -> {
            MenuEntity menuEntity = menuRepository.findById(s).get();
            if(!"0".equals(menuEntity.getParentId())){
                MenuAuthorityEntity authorityEntity = new MenuAuthorityEntity();
                authorityEntity.setMenuId(s);
                authorityEntity.setUserId(dto.getUserId());
                menuAuthorityRepository.save(authorityEntity);
            }
        });
//        redisUtil.del(dto.getLoginCode());
    }

    @Override
    public void buttonAuthority(AdminDTO dto) {
        buttonMapper.deleteByMenuIdAndUserId(dto);
        dto.getButtonList().forEach(s -> {
            ButtonAuthorityEntity authorityEntity = new ButtonAuthorityEntity();
            authorityEntity.setButtonId(s.getButtonId());
            authorityEntity.setMenuId(dto.getMenuId());
            authorityEntity.setUserId(dto.getUserId());
            buttonAuthorityRepository.save(authorityEntity);
        });
//        redisUtil.del(dto.getLoginCode());
    }

    @Override
    public List<String> getAdminMenuAuthority(String userId) {
        return menuAuthorityRepository.findByUserId(userId).stream().map(s -> s.getMenuId()).collect(Collectors.toList());
    }

}
