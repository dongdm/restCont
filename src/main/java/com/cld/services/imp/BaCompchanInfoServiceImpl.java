package com.cld.services.imp;

import com.cld.bean.BaCompchanInfo;
import com.cld.mapper.BaCompchanInfoMapper;
import com.cld.services.BaCompchanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dong on 2017/6/23.
 */
@Service
public class BaCompchanInfoServiceImpl implements BaCompchanInfoService {

    @Autowired
    private BaCompchanInfoMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BaCompchanInfo record) {
        return 0;
    }

    @Override
    public BaCompchanInfo insertSelective(String key, String value, String compChanId) {
        BaCompchanInfo record = new BaCompchanInfo();
        record.setInfoKey(key);
        record.setInfoValue(value);
        Integer id = new Integer(compChanId);
        record.setCompChanId(id);
        mapper.insertSelective(record);
        return record;
    }

    @Override
    public BaCompchanInfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public BaCompchanInfo updateByPrimaryKeySelective(String key, String value, String id) {
        BaCompchanInfo record = new BaCompchanInfo();
        record.setId(new Integer(id));
        record.setInfoKey(key);
        record.setInfoValue(value);
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public int updateByPrimaryKey(BaCompchanInfo record) {
        return 0;
    }

    @Override
    public List<BaCompchanInfo> selectByCompchanId(String compchanId) {
        BaCompchanInfo record = new BaCompchanInfo();
        record.setCompChanId(new Integer(compchanId));
        return mapper.selectByOther(record);
    }
}
