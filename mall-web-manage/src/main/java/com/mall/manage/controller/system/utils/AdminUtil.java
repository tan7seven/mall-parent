package com.mall.manage.controller.system.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.model.param.system.AdminCreateParam;
import com.mall.manage.model.param.system.AdminUpdateParam;
import com.mall.manage.model.vo.system.AdminDetailVO;
import com.mall.manage.model.vo.system.AdminPageVO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/22
 */
public class AdminUtil {
    /**
     * 分页查询结果
     */
    public static RestPage<AdminPageVO> buildPageVO(Page<AdminEntity> param){
        RestPage<AdminPageVO> result = new RestPage<>();
        if (Objects.isNull(param) || CollectionUtils.isEmpty(param.getRecords())) {
            return result;
        }
        List<AdminPageVO> voList = Lists.newArrayList();
        for (AdminEntity adminEntity : param.getRecords()) {
            AdminPageVO vo = buildVO(adminEntity);
            voList.add(vo);
        }
        result.setTotal(param.getTotal());
        result.setRecords(voList);
        return result;
    }
    private static AdminPageVO buildVO(AdminEntity entity){
        AdminPageVO vo = new AdminPageVO();
        vo.setCreateTime(entity.getCreateTime());
        vo.setLoginCode(entity.getLoginCode());
        vo.setModifyTime(entity.getModifyTime());
        vo.setName(entity.getName());
        vo.setPhone(entity.getPhone());
        vo.setRole(entity.getRole());
        vo.setUsabled(entity.getUsabled());
        vo.setId(entity.getId());
        return vo;
    }

    /**
     * 明细查询结果
     */
    public static AdminDetailVO buildDetailVO(AdminEntity entity){
        if (Objects.isNull(entity)) {
            return null;
        }
        AdminDetailVO vo = new AdminDetailVO();
        vo.setId(entity.getId());
        vo.setLoginCode(entity.getLoginCode());
        vo.setPassword(entity.getPassword());
        vo.setName(entity.getName());
        vo.setPhone(entity.getPhone());
        vo.setPicUrl(entity.getPicUrl());
        vo.setUsabled(entity.getUsabled());
        vo.setRole(entity.getRole());
        return vo;
    }

    /**
     * 新增参数
     * @param param
     * @return
     */
    public static AdminEntity buildCreateEntity(AdminCreateParam param){
        AdminEntity entity = new AdminEntity();
        entity.setUsabled(param.getUsabled());
        entity.setName(param.getName());
        entity.setPicUrl(param.getPicUrl());
        entity.setLoginCode(param.getLoginCode());
        entity.setPhone(param.getPhone());
        entity.setPassword(param.getPassword());
        entity.setRole(param.getRole());
        return entity;
    }

    /**
     * 修改参数
     * @param param
     * @return
     */
    public static AdminEntity buildUpdateEntity(AdminUpdateParam param){
        AdminEntity entity = new AdminEntity();
        entity.setId(param.getId());
        entity.setUsabled(param.getUsabled());
        entity.setName(param.getName());
        entity.setPicUrl(param.getPicUrl());
        entity.setPhone(param.getPhone());
        entity.setPassword(param.getPassword());
        entity.setRole(param.getRole());
        return entity;
    }
}
