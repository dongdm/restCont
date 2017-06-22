package com.cld.services;

import com.cld.bean.BaComp;

import java.util.List;

/**
 * Created by szb on 2017/6/20.
 */
public interface BaCompService {

    public BaComp search(BaComp baComp);

    public List<BaComp> find(BaComp baComp);

    void  add(BaComp baComp);

    void merge(BaComp baComp);
}
