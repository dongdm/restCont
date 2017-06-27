package com.cld.mapper;

import com.cld.bean.BaCompchanScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaCompchanScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanScore record);

    int insertSelective(BaCompchanScore record);

    BaCompchanScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanScore record);

    int updateByPrimaryKey(BaCompchanScore record);
}