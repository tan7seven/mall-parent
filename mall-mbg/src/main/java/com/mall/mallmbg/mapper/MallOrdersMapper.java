package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallOrders;
import com.mall.mallmbg.model.MallOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrdersMapper {
    int countByExample(MallOrdersExample example);

    int deleteByExample(MallOrdersExample example);

    int deleteByPrimaryKey(String ordersId);

    int insert(MallOrders record);

    int insertSelective(MallOrders record);

    List<MallOrders> selectByExample(MallOrdersExample example);

    MallOrders selectByPrimaryKey(String ordersId);

    int updateByExampleSelective(@Param("record") MallOrders record, @Param("example") MallOrdersExample example);

    int updateByExample(@Param("record") MallOrders record, @Param("example") MallOrdersExample example);

    int updateByPrimaryKeySelective(MallOrders record);

    int updateByPrimaryKey(MallOrders record);
}