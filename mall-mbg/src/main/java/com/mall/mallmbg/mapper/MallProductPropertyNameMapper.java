package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductPropertyName;
import com.mall.mallmbg.model.MallProductPropertyNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductPropertyNameMapper {
    int countByExample(MallProductPropertyNameExample example);

    int deleteByExample(MallProductPropertyNameExample example);

    int deleteByPrimaryKey(Integer propertyNameId);

    int insert(MallProductPropertyName record);

    int insertSelective(MallProductPropertyName record);

    List<MallProductPropertyName> selectByExample(MallProductPropertyNameExample example);

    MallProductPropertyName selectByPrimaryKey(Integer propertyNameId);

    int updateByExampleSelective(@Param("record") MallProductPropertyName record, @Param("example") MallProductPropertyNameExample example);

    int updateByExample(@Param("record") MallProductPropertyName record, @Param("example") MallProductPropertyNameExample example);

    int updateByPrimaryKeySelective(MallProductPropertyName record);

    int updateByPrimaryKey(MallProductPropertyName record);
}