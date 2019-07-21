package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallOrdersReturnApply;
import com.mall.mallmbg.model.MallOrdersReturnApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrdersReturnApplyMapper {
    int countByExample(MallOrdersReturnApplyExample example);

    int deleteByExample(MallOrdersReturnApplyExample example);

    int deleteByPrimaryKey(String applyId);

    int insert(MallOrdersReturnApply record);

    int insertSelective(MallOrdersReturnApply record);

    List<MallOrdersReturnApply> selectByExample(MallOrdersReturnApplyExample example);

    MallOrdersReturnApply selectByPrimaryKey(String applyId);

    int updateByExampleSelective(@Param("record") MallOrdersReturnApply record, @Param("example") MallOrdersReturnApplyExample example);

    int updateByExample(@Param("record") MallOrdersReturnApply record, @Param("example") MallOrdersReturnApplyExample example);

    int updateByPrimaryKeySelective(MallOrdersReturnApply record);

    int updateByPrimaryKey(MallOrdersReturnApply record);
}