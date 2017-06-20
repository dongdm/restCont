package com.cld.mapper;

import com.cld.bean.BaCompChanAuto;

public interface BaCompChanAutoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompChanAuto record);

    int insertSelective(BaCompChanAuto record);

    BaCompChanAuto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompChanAuto record);

    int updateByPrimaryKey(BaCompChanAuto record);
}