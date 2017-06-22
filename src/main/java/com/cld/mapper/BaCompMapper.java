package com.cld.mapper;

import com.cld.bean.BaComp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaCompMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaComp record);

    int insertSelective(BaComp record);

    BaComp search(BaComp record);

    List<BaComp> find(BaComp record);

    int updateByPrimaryKeySelective(BaComp record);

    int updateByPrimaryKey(BaComp record);
}