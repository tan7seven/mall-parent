package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.SystemMenuAuthority;
import com.mall.mallmbg.model.SystemMenuAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemMenuAuthorityMapper {
    int countByExample(SystemMenuAuthorityExample example);

    int deleteByExample(SystemMenuAuthorityExample example);

    int deleteByPrimaryKey(String menuAuthorityId);

    int insert(SystemMenuAuthority record);

    int insertSelective(SystemMenuAuthority record);

    List<SystemMenuAuthority> selectByExample(SystemMenuAuthorityExample example);

    SystemMenuAuthority selectByPrimaryKey(String menuAuthorityId);

    int updateByExampleSelective(@Param("record") SystemMenuAuthority record, @Param("example") SystemMenuAuthorityExample example);

    int updateByExample(@Param("record") SystemMenuAuthority record, @Param("example") SystemMenuAuthorityExample example);

    int updateByPrimaryKeySelective(SystemMenuAuthority record);

    int updateByPrimaryKey(SystemMenuAuthority record);
}