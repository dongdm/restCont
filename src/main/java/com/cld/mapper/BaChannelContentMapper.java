package com.cld.mapper;

import com.cld.bean.BaChannelContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaChannelContentMapper {
    int deleteByPrimaryKey(Integer id);

    int add(BaChannelContent record);

    int insertSelective(BaChannelContent record);

    BaChannelContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaChannelContent record);

    int updateByPrimaryKeyWithBLOBs(BaChannelContent record);

    int updateByPrimaryKey(BaChannelContent record);

    List<BaChannelContent> searchAll(BaChannelContent record);
}