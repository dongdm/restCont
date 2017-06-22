package com.cld.services.imp;

import com.cld.bean.BaComp;
import com.cld.bean.BaCompChanAuto;
import com.cld.mapper.BaCompMapper;
import com.cld.services.BaCompChanAutoService;
import com.cld.services.BaCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by szb on 2017/6/20.
 */
@Service
public class BaCompServiceImpl implements BaCompService {

    @Autowired
    private BaCompMapper baCompMapper;

    @Autowired
    private BaCompChanAutoService baCompChanAutoService;

    @Override
    public BaComp search(BaComp baComp) {
        return baCompMapper.search(baComp);
    }

    @Override
    public List<BaComp> find(BaComp baComp) {
        return baCompMapper.find(baComp);
    }

    @Override
    public void add(BaComp baComp) {
        Date date = new Date();
        baComp.setCreateby("0");
        baComp.setCreatedate(date);
        baComp.setUpdateby("0");
        baComp.setUpdatedate(date);
        baComp.setDeleflag("N");
        baCompMapper.insert(baComp);
        List<BaCompChanAuto> baCompChanAutoList =new ArrayList<BaCompChanAuto>();
        List<Integer> ids = baComp.getChanids();
        for (int i= 0 ;i<ids.size();i++ ){
            BaCompChanAuto baCompChanAuto = new BaCompChanAuto();
            baCompChanAuto.setCreateby("0");
            baCompChanAuto.setCreatedate(date);
            baCompChanAuto.setUpdateby("0");
            baCompChanAuto.setUpdatedate(date);
            baCompChanAuto.setDeleflag("N");
            baCompChanAuto.setChanId(ids.get(i));
            baCompChanAuto.setCompId(baComp.getId());
            baCompChanAutoList.add(baCompChanAuto);
        }
        baCompChanAutoService.batchAdd(baCompChanAutoList);
    }

    @Override
    public void merge(BaComp baComp) {
        baCompMapper.updateByPrimaryKeySelective(baComp);
    }
}
