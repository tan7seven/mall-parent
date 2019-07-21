package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductProperty;
import com.mall.mallmbg.model.MallProductPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductPropertyMapper {
    int countByExample(MallProductPropertyExample example);

    int deleteByExample(MallProductPropertyExample example);

    int deleteByPrimaryKey(Integer propertyId);

    int insert(MallProductProperty record);

    int insertSelective(MallProductProperty record);

    List<MallProductProperty> selectByExample(MallProductPropertyExample example);

    MallProductProperty selectByPrimaryKey(Integer propertyId);

    int updateByExampleSelective(@Param("record") MallProductProperty record, @Param("example") MallProductPropertyExample example);

    int updateByExample(@Param("record") MallProductProperty record, @Param("example") MallProductPropertyExample example);

    int updateByPrimaryKeySelective(MallProductProperty record);

    int updateByPrimaryKey(MallProductProperty record);
}