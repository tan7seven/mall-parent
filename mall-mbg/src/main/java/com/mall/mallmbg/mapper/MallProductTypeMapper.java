package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductType;
import com.mall.mallmbg.model.MallProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductTypeMapper {
    int countByExample(MallProductTypeExample example);

    int deleteByExample(MallProductTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(MallProductType record);

    int insertSelective(MallProductType record);

    List<MallProductType> selectByExample(MallProductTypeExample example);

    MallProductType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") MallProductType record, @Param("example") MallProductTypeExample example);

    int updateByExample(@Param("record") MallProductType record, @Param("example") MallProductTypeExample example);

    int updateByPrimaryKeySelective(MallProductType record);

    int updateByPrimaryKey(MallProductType record);
}