package com.cld.mapper;

import com.cld.bean.BaCompChanAuto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaCompChanAutoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompChanAuto record);

    int insertSelective(BaCompChanAuto record);

    BaCompChanAuto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompChanAuto record);

    int updateByPrimaryKey(BaCompChanAuto record);

    int batchInsert(List<BaCompChanAuto> record);
}