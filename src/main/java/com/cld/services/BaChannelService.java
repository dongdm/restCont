package com.cld.services;

import com.cld.bean.BaChannel;

import java.util.List;

/**
 * Created by dong on 2017/4/13.
 */
public interface BaChannelService {

     public BaChannel findById(int id);


     public List<BaChannel> searchAll(BaChannel baChannel);

     void add(BaChannel baChannel);

     void merge(BaChannel baChannel);

}
