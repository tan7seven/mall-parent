package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallAdmin;
import com.mall.mallmbg.model.MallAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallAdminMapper {
    int countByExample(MallAdminExample example);

    int deleteByExample(MallAdminExample example);

    int deleteByPrimaryKey(String userId);

    int insert(MallAdmin record);

    int insertSelective(MallAdmin record);

    List<MallAdmin> selectByExample(MallAdminExample example);

    MallAdmin selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") MallAdmin record, @Param("example") MallAdminExample example);

    int updateByExample(@Param("record") MallAdmin record, @Param("example") MallAdminExample example);

    int updateByPrimaryKeySelective(MallAdmin record);

    int updateByPrimaryKey(MallAdmin record);
}