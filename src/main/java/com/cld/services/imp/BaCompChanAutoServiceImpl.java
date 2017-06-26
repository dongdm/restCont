package com.cld.services.imp;

import com.cld.bean.BaCompChanAuto;
import com.cld.mapper.BaCompChanAutoMapper;
import com.cld.services.BaCompChanAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by szb on 2017/6/21.
 */
@Service
public class BaCompChanAutoServiceImpl implements BaCompChanAutoService {
    @Autowired
    private BaCompChanAutoMapper baCompChanAutoMapper;
    @Override
    public void add(BaCompChanAuto baCompChanAuto) {

        baCompChanAutoMapper.insert(baCompChanAuto);
    }

    @Override
    public void batchAdd(List<BaCompChanAuto> baCompChanAutoList) {

        baCompChanAutoMapper.batchInsert(baCompChanAutoList);
    }

    @Override
    public void merge(BaCompChanAuto baCompChanAuto) {

        baCompChanAutoMapper.updateByPrimaryKeySelective(baCompChanAuto);
    }

    @Override
    public List<BaCompChanAuto> queryByCompId(String compId) {
        BaCompChanAuto baCompChanAuto = new BaCompChanAuto();
        Integer compIdInt = new Integer(compId);
        baCompChanAuto.setCompId(compIdInt);
        return baCompChanAutoMapper.selectByCompId(baCompChanAuto);
    }
}
