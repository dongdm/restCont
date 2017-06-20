package com.cld.mapper;

import com.cld.bean.BaCompInfo;

public interface BaCompInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompInfo record);

    int insertSelective(BaCompInfo record);

    BaCompInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompInfo record);

    int updateByPrimaryKey(BaCompInfo record);
}