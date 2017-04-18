package com.cld.mapper;

import com.cld.bean.BaChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaChannel record);

    BaChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaChannel record);

    List<BaChannel> searchAll(BaChannel record);
}