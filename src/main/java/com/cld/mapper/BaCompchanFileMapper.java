package com.cld.mapper;

import com.cld.bean.BaCompchanFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaCompchanFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanFile record);

    int insertSelective(BaCompchanFile record);

    BaCompchanFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaCompchanFile record);

    int updateByPrimaryKey(BaCompchanFile record);
}