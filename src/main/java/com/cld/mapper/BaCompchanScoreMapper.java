package com.cld.mapper;

import com.cld.bean.BaCompchanScore;

public interface BaCompchanScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanScore record);

    int insertSelective(BaCompchanScore record);

    BaCompchanScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanScore record);

    int updateByPrimaryKey(BaCompchanScore record);
}