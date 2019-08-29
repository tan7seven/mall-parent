package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallUserScore;
import com.mall.mallmbg.model.MallUserScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserScoreMapper {
    int countByExample(MallUserScoreExample example);

    int deleteByExample(MallUserScoreExample example);

    int deleteByPrimaryKey(String userScoreId);

    int insert(MallUserScore record);

    int insertSelective(MallUserScore record);

    List<MallUserScore> selectByExample(MallUserScoreExample example);

    MallUserScore selectByPrimaryKey(String userScoreId);

    int updateByExampleSelective(@Param("record") MallUserScore record, @Param("example") MallUserScoreExample example);

    int updateByExample(@Param("record") MallUserScore record, @Param("example") MallUserScoreExample example);

    int updateByPrimaryKeySelective(MallUserScore record);

    int updateByPrimaryKey(MallUserScore record);
}