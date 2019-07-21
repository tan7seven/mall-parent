package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallOrdersOperationLog;
import com.mall.mallmbg.model.MallOrdersOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrdersOperationLogMapper {
    int countByExample(MallOrdersOperationLogExample example);

    int deleteByExample(MallOrdersOperationLogExample example);

    int deleteByPrimaryKey(String operationId);

    int insert(MallOrdersOperationLog record);

    int insertSelective(MallOrdersOperationLog record);

    List<MallOrdersOperationLog> selectByExample(MallOrdersOperationLogExample example);

    MallOrdersOperationLog selectByPrimaryKey(String operationId);

    int updateByExampleSelective(@Param("record") MallOrdersOperationLog record, @Param("example") MallOrdersOperationLogExample example);

    int updateByExample(@Param("record") MallOrdersOperationLog record, @Param("example") MallOrdersOperationLogExample example);

    int updateByPrimaryKeySelective(MallOrdersOperationLog record);

    int updateByPrimaryKey(MallOrdersOperationLog record);
}