package com.cld.services;

import com.cld.bean.BaCompchanScore;

/**
 * Created by dong on 2017/6/27.
 */
public interface BaCompchanScoreService {
    int deleteByPrimaryKey(Integer id);

    int insert(BaCompchanScore record);

    BaCompchanScore insertSelective(String compchanId, String score, String desc);

    BaCompchanScore selectByPrimaryKey(Integer id);

    BaCompchanScore updateByPrimaryKeySelective(String id, String score, String desc) ;

    int updateByPrimaryKey(BaCompchanScore record);
}
