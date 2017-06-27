package com.cld.services.imp;

import com.cld.bean.BaCompchanScore;
import com.cld.mapper.BaCompchanScoreMapper;
import com.cld.services.BaCompchanScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dong on 2017/6/27.
 */
@Service
public class BaCompchanScoreServiceImpl implements BaCompchanScoreService {

    @Autowired
    private BaCompchanScoreMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(BaCompchanScore record) {
        return 0;
    }

    @Override
    public BaCompchanScore insertSelective(String compchanId, String score, String desc) {
        BaCompchanScore record = new BaCompchanScore();
        record.setCompChanId(new Integer(compchanId));
        record.setScore(new Integer(score));
        record.setNodeName(desc);
        mapper.insertSelective(record);
        return record;
    }

    @Override
    public BaCompchanScore selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public BaCompchanScore updateByPrimaryKeySelective(String id, String score, String desc) {
        BaCompchanScore record = new BaCompchanScore();
        record.setId(new Integer(id));
        record.setScore(new Integer(score));
        record.setNodeName(desc);
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public int updateByPrimaryKey(BaCompchanScore record) {
        return 0;
    }
}
