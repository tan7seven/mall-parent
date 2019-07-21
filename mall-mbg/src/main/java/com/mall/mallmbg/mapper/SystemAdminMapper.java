package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.SystemAdmin;
import com.mall.mallmbg.model.SystemAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemAdminMapper {
    int countByExample(SystemAdminExample example);

    int deleteByExample(SystemAdminExample example);

    int deleteByPrimaryKey(String userId);

    int insert(SystemAdmin record);

    int insertSelective(SystemAdmin record);

    List<SystemAdmin> selectByExample(SystemAdminExample example);

    SystemAdmin selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") SystemAdmin record, @Param("example") SystemAdminExample example);

    int updateByExample(@Param("record") SystemAdmin record, @Param("example") SystemAdminExample example);

    int updateByPrimaryKeySelective(SystemAdmin record);

    int updateByPrimaryKey(SystemAdmin record);
}