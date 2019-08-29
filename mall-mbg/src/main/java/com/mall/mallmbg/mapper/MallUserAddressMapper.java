package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallUserAddress;
import com.mall.mallmbg.model.MallUserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallUserAddressMapper {
    int countByExample(MallUserAddressExample example);

    int deleteByExample(MallUserAddressExample example);

    int deleteByPrimaryKey(String userAddressId);

    int insert(MallUserAddress record);

    int insertSelective(MallUserAddress record);

    List<MallUserAddress> selectByExample(MallUserAddressExample example);

    MallUserAddress selectByPrimaryKey(String userAddressId);

    int updateByExampleSelective(@Param("record") MallUserAddress record, @Param("example") MallUserAddressExample example);

    int updateByExample(@Param("record") MallUserAddress record, @Param("example") MallUserAddressExample example);

    int updateByPrimaryKeySelective(MallUserAddress record);

    int updateByPrimaryKey(MallUserAddress record);
}