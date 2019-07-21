package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.SystemButtonAuthority;
import com.mall.mallmbg.model.SystemButtonAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemButtonAuthorityMapper {
    int countByExample(SystemButtonAuthorityExample example);

    int deleteByExample(SystemButtonAuthorityExample example);

    int deleteByPrimaryKey(String buttonAuthorityId);

    int insert(SystemButtonAuthority record);

    int insertSelective(SystemButtonAuthority record);

    List<SystemButtonAuthority> selectByExample(SystemButtonAuthorityExample example);

    SystemButtonAuthority selectByPrimaryKey(String buttonAuthorityId);

    int updateByExampleSelective(@Param("record") SystemButtonAuthority record, @Param("example") SystemButtonAuthorityExample example);

    int updateByExample(@Param("record") SystemButtonAuthority record, @Param("example") SystemButtonAuthorityExample example);

    int updateByPrimaryKeySelective(SystemButtonAuthority record);

    int updateByPrimaryKey(SystemButtonAuthority record);
}