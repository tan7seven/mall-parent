package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.PrizeActivityTime;
import com.mall.mallmbg.model.PrizeActivityTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrizeActivityTimeMapper {
    int countByExample(PrizeActivityTimeExample example);

    int deleteByExample(PrizeActivityTimeExample example);

    int deleteByPrimaryKey(String userId);

    int insert(PrizeActivityTime record);

    int insertSelective(PrizeActivityTime record);

    List<PrizeActivityTime> selectByExample(PrizeActivityTimeExample example);

    PrizeActivityTime selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") PrizeActivityTime record, @Param("example") PrizeActivityTimeExample example);

    int updateByExample(@Param("record") PrizeActivityTime record, @Param("example") PrizeActivityTimeExample example);

    int updateByPrimaryKeySelective(PrizeActivityTime record);

    int updateByPrimaryKey(PrizeActivityTime record);
}