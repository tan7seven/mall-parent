package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductPropertyValue;
import com.mall.mallmbg.model.MallProductPropertyValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductPropertyValueMapper {
    int countByExample(MallProductPropertyValueExample example);

    int deleteByExample(MallProductPropertyValueExample example);

    int deleteByPrimaryKey(Integer propertyValueId);

    int insert(MallProductPropertyValue record);

    int insertSelective(MallProductPropertyValue record);

    List<MallProductPropertyValue> selectByExample(MallProductPropertyValueExample example);

    MallProductPropertyValue selectByPrimaryKey(Integer propertyValueId);

    int updateByExampleSelective(@Param("record") MallProductPropertyValue record, @Param("example") MallProductPropertyValueExample example);

    int updateByExample(@Param("record") MallProductPropertyValue record, @Param("example") MallProductPropertyValueExample example);

    int updateByPrimaryKeySelective(MallProductPropertyValue record);

    int updateByPrimaryKey(MallProductPropertyValue record);
}