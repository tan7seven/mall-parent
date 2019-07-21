package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.SystemButton;
import com.mall.mallmbg.model.SystemButtonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemButtonMapper {
    int countByExample(SystemButtonExample example);

    int deleteByExample(SystemButtonExample example);

    int deleteByPrimaryKey(String buttonId);

    int insert(SystemButton record);

    int insertSelective(SystemButton record);

    List<SystemButton> selectByExample(SystemButtonExample example);

    SystemButton selectByPrimaryKey(String buttonId);

    int updateByExampleSelective(@Param("record") SystemButton record, @Param("example") SystemButtonExample example);

    int updateByExample(@Param("record") SystemButton record, @Param("example") SystemButtonExample example);

    int updateByPrimaryKeySelective(SystemButton record);

    int updateByPrimaryKey(SystemButton record);
}