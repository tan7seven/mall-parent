package com.mall.mallmbg.mapper;

import com.mall.mallmbg.model.MallProductImg;
import com.mall.mallmbg.model.MallProductImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallProductImgMapper {
    int countByExample(MallProductImgExample example);

    int deleteByExample(MallProductImgExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(MallProductImg record);

    int insertSelective(MallProductImg record);

    List<MallProductImg> selectByExample(MallProductImgExample example);

    MallProductImg selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") MallProductImg record, @Param("example") MallProductImgExample example);

    int updateByExample(@Param("record") MallProductImg record, @Param("example") MallProductImgExample example);

    int updateByPrimaryKeySelective(MallProductImg record);

    int updateByPrimaryKey(MallProductImg record);
}