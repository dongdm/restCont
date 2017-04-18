package com.cld.services.imp;

import com.cld.bean.BaChannel;
import com.cld.mapper.BaChannelMapper;
import com.cld.services.BaChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by dong on 2017/4/13.
 */
@Service
public class BaChannelServiceImpl implements BaChannelService {

    @Autowired
    private BaChannelMapper baChannelMapper;

    public BaChannel findById(int id) {
        return baChannelMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<BaChannel> searchAll(BaChannel baChannel) {
        return baChannelMapper.searchAll(baChannel);
    }

    public void add(BaChannel baChannel) {
        Date date = new Date();
        baChannel.setCreateby("0");
        baChannel.setCreatedate(date);
        baChannel.setUpdateby("0");
        baChannel.setUpdatedate(date);
        baChannel.setDeleflag("N");
        baChannel.setCode("000x");
        baChannel.setType("book");
         baChannelMapper.insert(baChannel);
    }

    public void merge(BaChannel baChannel) {
        Date date = new Date();
        baChannel.setUpdateby("0");
        baChannel.setUpdatedate(date);
        baChannelMapper.updateByPrimaryKeySelective(baChannel);
    }

}
