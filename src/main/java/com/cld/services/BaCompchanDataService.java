package com.cld.services;

import com.cld.bean.BaCompchanData;

/**
 * Created by dong on 2017/6/23.
 */
public interface BaCompchanDataService {

    int deleteByPrimaryKey(Integer id);

    BaCompchanData insert(BaCompchanData record);

    BaCompchanData insertSelective(String content, String compChanDataId);

    BaCompchanData selectByPrimaryKey(Integer id);

    BaCompchanData updateByPrimaryKeySelective(String content, String compChanDataId);

    BaCompchanData updateByPrimaryKeyWithBLOBs(BaCompchanData record);

    int updateByPrimaryKey(BaCompchanData record);

    BaCompchanData selectByCompchanId(String compchanId);

}
