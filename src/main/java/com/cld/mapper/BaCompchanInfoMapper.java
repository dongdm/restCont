package com.cld.mapper;

import com.cld.bean.BaCompchanInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaCompchanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanInfo record);

    int insertSelective(BaCompchanInfo record);

    BaCompchanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanInfo record);

    int updateByPrimaryKey(BaCompchanInfo record);
}