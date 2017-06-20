package com.cld.mapper;

import com.cld.bean.BaComp;

public interface BaCompMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaComp record);

    int insertSelective(BaComp record);

    BaComp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaComp record);

    int updateByPrimaryKey(BaComp record);
}