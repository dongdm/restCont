package com.cld.services;

import com.cld.bean.BaCompChanAuto;

import java.util.List;

/**
 * Created by szb on 2017/6/21.
 */
public interface BaCompChanAutoService {

    void add(BaCompChanAuto baCompChanAuto);

    void batchAdd(List<BaCompChanAuto> baCompChanAutoList);

    void merge(BaCompChanAuto baCompChanAuto);
}
