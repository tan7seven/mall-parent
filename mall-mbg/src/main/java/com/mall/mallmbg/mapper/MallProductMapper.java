package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProduct;
import com.mall.mallmbg.model.MallProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductMapper {
    int countByExample(MallProductExample example);

    int deleteByExample(MallProductExample example);

    int deleteByPrimaryKey(Integer productId);

    int insert(MallProduct record);

    int insertSelective(MallProduct record);

    List<MallProduct> selectByExample(MallProductExample example);

    MallProduct selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") MallProduct record, @Param("example") MallProductExample example);

    int updateByExample(@Param("record") MallProduct record, @Param("example") MallProductExample example);

    int updateByPrimaryKeySelective(MallProduct record);

    int updateByPrimaryKey(MallProduct record);
}