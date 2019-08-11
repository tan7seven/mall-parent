package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallOrdersSetting;
import com.mall.mallmbg.model.MallOrdersSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrdersSettingMapper {
    int countByExample(MallOrdersSettingExample example);

    int deleteByExample(MallOrdersSettingExample example);

    int deleteByPrimaryKey(Integer settingId);

    int insert(MallOrdersSetting record);

    int insertSelective(MallOrdersSetting record);

    List<MallOrdersSetting> selectByExample(MallOrdersSettingExample example);

    MallOrdersSetting selectByPrimaryKey(Integer settingId);

    int updateByExampleSelective(@Param("record") MallOrdersSetting record, @Param("example") MallOrdersSettingExample example);

    int updateByExample(@Param("record") MallOrdersSetting record, @Param("example") MallOrdersSettingExample example);

    int updateByPrimaryKeySelective(MallOrdersSetting record);

    int updateByPrimaryKey(MallOrdersSetting record);
}