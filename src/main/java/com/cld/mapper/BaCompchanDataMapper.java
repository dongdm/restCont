package com.cld.mapper;

import com.cld.bean.BaCompchanData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaCompchanDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanData record);

    int insertSelective(BaCompchanData record);

    BaCompchanData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanData record);

    int updateByPrimaryKeyWithBLOBs(BaCompchanData record);

    int updateByPrimaryKey(BaCompchanData record);

    BaCompchanData selectByOther(BaCompchanData record);
}