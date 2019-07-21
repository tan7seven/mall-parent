package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductSku;
import com.mall.mallmbg.model.MallProductSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductSkuMapper {
    int countByExample(MallProductSkuExample example);

    int deleteByExample(MallProductSkuExample example);

    int deleteByPrimaryKey(Integer skuId);

    int insert(MallProductSku record);

    int insertSelective(MallProductSku record);

    List<MallProductSku> selectByExample(MallProductSkuExample example);

    MallProductSku selectByPrimaryKey(Integer skuId);

    int updateByExampleSelective(@Param("record") MallProductSku record, @Param("example") MallProductSkuExample example);

    int updateByExample(@Param("record") MallProductSku record, @Param("example") MallProductSkuExample example);

    int updateByPrimaryKeySelective(MallProductSku record);

    int updateByPrimaryKey(MallProductSku record);
}