package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.CompanyAddress;
import com.mall.mallmbg.model.CompanyAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyAddressMapper {
    int countByExample(CompanyAddressExample example);

    int deleteByExample(CompanyAddressExample example);

    int deleteByPrimaryKey(String addressId);

    int insert(CompanyAddress record);

    int insertSelective(CompanyAddress record);

    List<CompanyAddress> selectByExample(CompanyAddressExample example);

    CompanyAddress selectByPrimaryKey(String addressId);

    int updateByExampleSelective(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByExample(@Param("record") CompanyAddress record, @Param("example") CompanyAddressExample example);

    int updateByPrimaryKeySelective(CompanyAddress record);

    int updateByPrimaryKey(CompanyAddress record);
}