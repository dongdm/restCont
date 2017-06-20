package com.cld.services.imp;

import com.cld.bean.BaChannelContent;
import com.cld.mapper.BaChannelContentMapper;
import com.cld.services.BaChannelContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by szb on 2017/6/20.
 */
@Service
public class BaChannelContentServiceImpl implements BaChannelContentService {

    @Autowired
    private BaChannelContentMapper baChannelContentMapper;

    @Cacheable(value = "baChannelContent")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public BaChannelContent findById(int id) {
        System.out.print("是从数据库拿到的数据--QAQ--");
        return baChannelContentMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<BaChannelContent> searchAll(BaChannelContent baChannelContent) {
        return baChannelContentMapper.searchAll(baChannelContent);
    }

    public void add(BaChannelContent baChannelContent) {
        Date date = new Date();
        baChannelContent.setUserId(0);
        baChannelContent.setCreateby("0");
        baChannelContent.setCreatedate(date);
        baChannelContent.setUpdateby("0");
        baChannelContent.setUpdatedate(date);
        baChannelContent.setDeleflag("N");
        baChannelContentMapper.add(baChannelContent);
    }

    public void merge(BaChannelContent baChannel) {
        Date date = new Date();
        baChannel.setUpdateby("0");
        baChannel.setUpdatedate(date);
        baChannelContentMapper.updateByPrimaryKeySelective(baChannel);
    }

}
