package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallOrdersItems;
import com.mall.mallmbg.model.MallOrdersItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrdersItemsMapper {
    int countByExample(MallOrdersItemsExample example);

    int deleteByExample(MallOrdersItemsExample example);

    int deleteByPrimaryKey(String itemsId);

    int insert(MallOrdersItems record);

    int insertSelective(MallOrdersItems record);

    List<MallOrdersItems> selectByExample(MallOrdersItemsExample example);

    MallOrdersItems selectByPrimaryKey(String itemsId);

    int updateByExampleSelective(@Param("record") MallOrdersItems record, @Param("example") MallOrdersItemsExample example);

    int updateByExample(@Param("record") MallOrdersItems record, @Param("example") MallOrdersItemsExample example);

    int updateByPrimaryKeySelective(MallOrdersItems record);

    int updateByPrimaryKey(MallOrdersItems record);
}