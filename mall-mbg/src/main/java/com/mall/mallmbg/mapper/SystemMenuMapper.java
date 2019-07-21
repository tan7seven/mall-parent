package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.SystemMenu;
import com.mall.mallmbg.model.SystemMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemMenuMapper {
    int countByExample(SystemMenuExample example);

    int deleteByExample(SystemMenuExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    List<SystemMenu> selectByExample(SystemMenuExample example);

    SystemMenu selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") SystemMenu record, @Param("example") SystemMenuExample example);

    int updateByExample(@Param("record") SystemMenu record, @Param("example") SystemMenuExample example);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);
}