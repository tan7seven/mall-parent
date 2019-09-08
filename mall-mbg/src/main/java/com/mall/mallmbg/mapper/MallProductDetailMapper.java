package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductDetail;
import com.mall.mallmbg.model.MallProductDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductDetailMapper {
    int countByExample(MallProductDetailExample example);

    int deleteByExample(MallProductDetailExample example);

    int deleteByPrimaryKey(Integer detailId);

    int insert(MallProductDetail record);

    int insertSelective(MallProductDetail record);

    List<MallProductDetail> selectByExampleWithBLOBs(MallProductDetailExample example);

    List<MallProductDetail> selectByExample(MallProductDetailExample example);

    MallProductDetail selectByPrimaryKey(Integer detailId);

    int updateByExampleSelective(@Param("record") MallProductDetail record, @Param("example") MallProductDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") MallProductDetail record, @Param("example") MallProductDetailExample example);

    int updateByExample(@Param("record") MallProductDetail record, @Param("example") MallProductDetailExample example);

    int updateByPrimaryKeySelective(MallProductDetail record);

    int updateByPrimaryKeyWithBLOBs(MallProductDetail record);

    int updateByPrimaryKey(MallProductDetail record);
}