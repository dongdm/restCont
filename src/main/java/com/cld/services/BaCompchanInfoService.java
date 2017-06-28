package com.cld.services;

import com.cld.bean.BaCompchanInfo;

import java.util.List;

/**
 * Created by dong on 2017/6/23.
 */
public interface BaCompchanInfoService {

    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanInfo record);

    BaCompchanInfo insertSelective(String key, String value, String compChanId);

    BaCompchanInfo selectByPrimaryKey(Integer id);

    BaCompchanInfo updateByPrimaryKeySelective(String key, String value, String id);

    int updateByPrimaryKey(BaCompchanInfo record);

    List<BaCompchanInfo> selectByCompchanId(String compchanId);
}
