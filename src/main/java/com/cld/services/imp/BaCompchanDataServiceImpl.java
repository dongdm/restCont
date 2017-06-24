package com.cld.services.imp;

import com.cld.bean.BaCompchanData;
import com.cld.mapper.BaCompchanDataMapper;
import com.cld.services.BaCompchanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dong on 2017/6/23.
 */
@Service
public class BaCompchanDataServiceImpl implements BaCompchanDataService {

    @Autowired
    private BaCompchanDataMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public BaCompchanData insert(BaCompchanData record) {
        return null;
    }

    @Override
    public BaCompchanData insertSelective(String content, String compChanDataId) {
        BaCompchanData record = new BaCompchanData();
        record.setCompChanId(new Integer(compChanDataId));
        record.setComtent(content);
        mapper.insertSelective(record);
        return record;
    }

    @Override
    public BaCompchanData selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public BaCompchanData updateByPrimaryKeySelective(String content, String id){
        BaCompchanData record = new BaCompchanData();
        record.setId(new Integer(id));
        record.setComtent(content);
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public BaCompchanData updateByPrimaryKeyWithBLOBs(BaCompchanData record) {
       return null;
    }

    @Override
    public int updateByPrimaryKey(BaCompchanData record) {
        return 0;
    }
}
