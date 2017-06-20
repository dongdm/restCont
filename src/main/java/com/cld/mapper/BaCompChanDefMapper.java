package com.cld.mapper;

import com.cld.bean.BaCompChanDef;

public interface BaCompChanDefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompChanDef record);

    int insertSelective(BaCompChanDef record);

    BaCompChanDef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompChanDef record);

    int updateByPrimaryKey(BaCompChanDef record);
}